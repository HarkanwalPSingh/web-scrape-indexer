# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: https://docs.scrapy.org/en/latest/topics/item-pipeline.html


# useful for handling different item types with a single interface
import json
import requests
import logging
from itemadapter import ItemAdapter

LOCAL_HOST = "http://localhost:8080"
INDEX_PATH = "/news-indexer/index"

logger = logging.getLogger(__name__)
logger.setLevel(logging.DEBUG)


class JsonWritePipeline:
    def open_spider(self, spider):
        self.file = open("news_items.jsonl", "w")

    def close_spider(self, spider):
        self.file.close()

    def process_item(self, item, spider):
        line = json.dumps(ItemAdapter(item).asdict()) + "\n"
        self.file.write(line)
        return item


class LocalIndexerPipeline:
    def open_spider(self, spider):
        pass

    def close_spider(self, spider):
        pass

    def process_item(self, item, spider):
        index_url = LOCAL_HOST + INDEX_PATH
        response = requests.post(index_url, json=ItemAdapter(item).asdict())
        logger.debug(response.json())

        return item
