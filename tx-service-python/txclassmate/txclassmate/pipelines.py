# -*- coding: utf-8 -*-
from txclassmate.utils.db_util import Config

import logging

LOG_FORMAT = '%(asctime)s [%(threadName)s] [%(levelname)s] %(filename)s[line:%(lineno)d]: %(message)s'
DATE_FORMAT = "%Y-%m-%d %H:%M:%S %p"
fs = logging.StreamHandler()
logging.basicConfig(level=logging.DEBUG, format=LOG_FORMAT, datefmt=DATE_FORMAT, handlers=[fs])

# TODO 腾讯课堂采用custom-string字体转换,后端返回的数字做了处理,故采集后,需要重新映射
number_dict = {1: 6, 2: 0, 3: 2, 4: 7, 5: 1, 6: 8, 7: 9, 8: 3, 9: 5, 0: 4}


class TxclassmatePipeline(object):

    def __init__(self) -> None:
        config = Config()
        conn = config.getConnention()
        self.conn = conn

    def process_item(self, item, spider):
        agency = item['agency'][0]
        if item['price'] is None or len(item['price']) == 0 or item['price'][0] == "免费":
            price = 0
        else:
            price = str(item['price'][0]).replace("¥", "")
            print(price)
            p_number = []
            if len(price.split(".")) == 2:
                s_number = []
                for n in price.split(".")[0]:
                    p_number.append(str(number_dict.get(int(n))))

                for s in price.split(".")[1]:
                    s_number.append(str(number_dict.get(int(s))))
                price = "".join(tuple(p_number)) + "." + "".join(tuple(s_number))
            else:
                for n in price.split(".")[0]:
                    p_number.append(str(number_dict.get(int(n))))
                price = "".join(p_number)

        title = item['title'][0]

        users = str(item['users'][0]).replace("\n            ", "").replace(" ", "")
        print(users)
        u_number = []
        for n in users.split("人")[0]:
            u_number.append(str(number_dict.get(int(n))))
        users = "".join(u_number)+"人"+users.split("人")[1]
        print(users)

        link = item['link'][0]
        seed_id = item['seed_id']
        cursor = self.conn.cursor()
        insert_sql = """
                        insert into tx_classmate(link, price, users ,agency,title,c_seed_id)
                        VALUES(%s, %s, %s, %s,%s,%s)
                     """
        try:
            cursor.execute(insert_sql, (link, price, users, agency, title, seed_id))
            self.conn.commit()
        except Exception as e:
            logging.error("insert data error...", e)
            self.conn.rollback()
        finally:
            cursor.close()
        return item

    def close_spider(self, spider):
        self.conn.close()
