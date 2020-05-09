from scrapy.cmdline import execute
import sys, os

if __name__ == "__main__":
    print("start executor model %s main..." %(sys.argv[1]))
    sys.path.append(os.path.dirname(os.path.abspath(__file__)))
    execute((['scrapy', 'crawl', sys.argv[1]]))
