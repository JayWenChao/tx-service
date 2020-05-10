# -*- coding: utf-8 -*-
import scrapy
from scrapy import Request
import pymysql

from txclassmate.items import txDataItem
from txclassmate.utils.db_util import Config

urls_tempalte = 'https://ke.qq.com/course/list?mt={0}&st={1}'

class ClassmateSpider(scrapy.Spider):
    name = 'classmate'
    allowed_domains = ['ke.qq.com']

    def __init__(self, name=None, **kwargs):
        super().__init__(name, **kwargs)
        config = Config()
        self.conn = config.getConnention()

    def start_requests(self):
        cursor = self.conn.cursor(pymysql.cursors.DictCursor)
        sql = "select *from tx_meta_class"
        cursor.execute(sql)
        result = cursor.fetchall()
        cursor.close()
        for r in result:
            st = r['c_seed_id']
            url = urls_tempalte.format(r['p_seed_id'], st)
            request = scrapy.Request(url=url, callback=self.parse)
            request.meta['seed_id'] = st
            yield request
        print("url len [%s] start request url..." % (len(result)))

    def parse(self, response):
        print("start parse data...")
        item = txDataItem()
        if response.status == 200:
            try:
                seed_id = response.meta['seed_id']
            except Exception as e:
                print("get metadata exception...",e)
                #todo 元数据获取异常,用链接切割
                seed_id = str(response.url).split("&")[1].split("=")[1]
            for r in response.xpath('//div[@class="market-bd market-bd-6 course-list course-card-list-multi-wrap js-course-list"]//li[@class="course-card-item--v3 js-course-card-item "]'):
                item['seed_id'] = seed_id
                item['title'] = r.xpath('./h4/a/@title').extract()
                item['users'] = r.xpath('./div/span[@class="line-cell item-user custom-string"]/text()').extract()
                item['price'] = r.xpath('./div/span[@class="line-cell item-price  custom-string"]/text()').extract()
                item['agency'] = r.xpath(
                    './div[@class="item-line item-line--middle"]/a[contains(@class,"item-source-link")]/@title').extract()
                item['link'] = r.xpath('./a/@href').extract()
                yield item

            next_page = response.xpath('//div[@class="sort-page"]/a[last()]/@href').extract()[0]
            if next_page != "javascript:void(0);":
                yield Request(next, callback=self.parse)
            else:
                print("not found next page...")
        else:
            print("ip may error...")

    def close(self, spider, reason):
        print("close connection...")
        self.conn.close()

