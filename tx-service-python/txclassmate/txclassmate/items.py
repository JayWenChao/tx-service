# -*- coding: utf-8 -*-

# Define here the models for your scraped items
# QErMglwA0Fx
# See documentation in:
# https://doc.scrapy.org/en/latest/topics/items.html

import scrapy



class txDataItem(scrapy.Item):
    #标题
    title = scrapy.Field()
    #价格
    price = scrapy.Field()
    #报名人数 &购买人数
    users = scrapy.Field()
    #课程所属机构
    agency = scrapy.Field()
    #课程连接
    link = scrapy.Field()
    # 种子id
    seed_id = scrapy.Field()


