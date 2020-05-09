# -*- coding: utf-8 -*-
from txclassmate.utils.db_util import Config

import logging

LOG_FORMAT = '%(asctime)s [%(threadName)s] [%(levelname)s] %(filename)s[line:%(lineno)d]: %(message)s'
DATE_FORMAT = "%Y-%m-%d %H:%M:%S %p"
fs = logging.StreamHandler()
logging.basicConfig(level=logging.DEBUG, format=LOG_FORMAT, datefmt=DATE_FORMAT, handlers=[fs])


class TxclassmatePipeline(object):

    def __init__(self) -> None:
        config = Config()
        conn = config.getConnention()
        self.conn = conn

    def process_item(self, item, spider):
        agency = item['agency'][0]
        price = -1
        if item['price'] is None or len(item['price']) == 0 or item['price'][0] == "免费":
            price = 0
        else:
            price = str(item['price'][0]).replace("¥", "")

        title = item['title'][0]
        users = str(item['users'][0]).replace("\n            ", "").replace(" ", "")
        link = item['link'][0]
        seed_id = item['seed_id']
        cursor = self.conn.cursor()
        insert_sql = """
                        insert into tx_classmate(link, price, users ,agency,title,c_seed_id)
                        VALUES(%s, %s, %s, %s,%s,%s)
                     """
        try:
            cursor.execute(insert_sql, (link, price, users, agency, title,seed_id))
            self.conn.commit()
        except Exception as e:
            logging.error("insert data error...", e)
            self.conn.rollback()
        finally:
            cursor.close()
        return item

    def close_spider(self, spider):
        self.conn.close()
