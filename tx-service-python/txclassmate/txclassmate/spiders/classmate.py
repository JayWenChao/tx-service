# -*- coding: utf-8 -*-
import scrapy
from scrapy import Request

from txclassmate.items import txDataItem


class ClassmateSpider(scrapy.Spider):
    name = 'classmate'
    allowed_domains = ['ke.qq.com']
    start_urls = ['https://ke.qq.com/course/list?mt=1001']

    def parse(self, response):

        item = txDataItem()
        if response.status == 200:
            for r in response.xpath('//div[@class="market-bd market-bd-6 course-list course-card-list-multi-wrap js-course-list"]//li[@class="course-card-item--v3 js-course-card-item "]'):
                item['title'] = r.xpath('./h4/a/@title').extract()
            item['users'] = r.xpath('./div/span[@class="line-cell item-user custom-string"]/text()').extract()
            item['price'] = r.xpath('./div/span[@class="line-cell item-price  custom-string"]/text()').extract()
            item['agency'] = r.xpath('./div[@class="item-line item-line--middle"]/a[contains(@class,"item-source-link")]/@title').extract()
            item['link'] = r.xpath('./a/@href').extract()
            yield item

            sum_page = response.xpath('//div[@class="sort-page"]/a[last()]/@href').extract()[0]
            if sum_page != "javascript:void(0);":
                yield Request(sum_page,callback=self.parse)
            else:
                print("not found next page...")
        else:
            print("ip may error...")

