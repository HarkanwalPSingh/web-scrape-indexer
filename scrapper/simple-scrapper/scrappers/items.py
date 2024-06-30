# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html
import json

import scrapy
from itemloaders.processors import MapCompose, Join, TakeFirst


class NewsItem(scrapy.Item):
    url = scrapy.Field(
        output_processor=TakeFirst()
    )
    headlines = scrapy.Field(
        output_processor=TakeFirst()
    )
    content = scrapy.Field(
        output_processor=Join("")
    )
    timestamp = scrapy.Field(
        output_processor=TakeFirst()
    )
