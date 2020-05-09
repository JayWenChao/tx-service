import configparser

import pymysql.cursors
from DBUtils.PooledDB import PooledDB

global_pool = None

# 暂时不配置成模块了
import logging

LOG_FORMAT = '%(asctime)s [%(threadName)s] [%(levelname)s] %(filename)s[line:%(lineno)d]: %(message)s'
DATE_FORMAT = "%Y-%m-%d %H:%M:%S %p"
fs = logging.StreamHandler()
logging.basicConfig(level=logging.DEBUG, format=LOG_FORMAT, datefmt=DATE_FORMAT, handlers=[fs])


class Config:

    def __init__(self) -> None:
        config = configparser.ConfigParser()
        config.read("config.ini")
        self.host = config.get('mysql', 'host')
        self.user = config.get('mysql', 'user')
        self.port = config.get('mysql', 'port')
        self.charset = config.get('mysql', 'charset')
        self.password = config.get('mysql', 'password')
        self.db_name = config.get('mysql', 'db_name')

    def getConnention(self):
        logging.info("start get db connection")
        global global_pool
        if global_pool == None:
            global_pool = PooledDB(
                creator=pymysql,
                host=self.host,
                port=int(self.port),
                user=self.user,
                password=self.password,
                database=self.db_name,
                charset=self.charset
            )
            db_conn = global_pool.connection()
            return db_conn
        else:
            return global_pool.connection()
