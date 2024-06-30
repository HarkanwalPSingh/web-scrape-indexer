import logging
import os

import scrapy
from datetime import datetime

from scrapy.http import Response
from scrapy.loader import ItemLoader

from scrappers.items import NewsItem


class NewsSpider(scrapy.Spider):
    name = "news-spider"
    # start_urls = os.getenv('START_URLS').split(',')
    # allowed_domains = os.getenv('ALLOWED_DOMAINS').split(',')
    start_urls = ["https://www.hindustantimes.com/cities/bengaluru-news"]
    allowed_domains = ["hindustantimes.com"]

    def parse(self, response: Response):
        mainContainer = response.css("section.mainContainer")
        article_anchors = mainContainer.css("div.cartHolder")
        # anchors = article_anchors.css("h3.hdg3 a::attr(href)").getall()

        article_page_links = article_anchors.css("h3.hdg3 a")

        yield from response.follow_all(article_page_links, self.parse_news)

        # filename = "article-anchors.txt"
        # Path(filename).write_text("\n".join(anchors))

    def parse_news(self, response):
        def extract_with_css(query):
            return response.css(query).get(default="").strip()

        mainContainer = response.css("section.mainContainer")

        loader = ItemLoader(NewsItem(), response=response)

        loader.add_value("url", response.url)
        loader.add_css("headlines", "section.mainContainer div#dataHolder h1::text")
        loader.add_css("content", "section.mainContainer p::text")
        timestamp = mainContainer.css("div.actionDiv div.dateTime::text").get()
        loader.add_value("timestamp", self.convert_timestamp(timestamp))

        yield loader.load_item()

    def convert_timestamp(self, date_string):
        date_string = date_string.strip()

        date_format = '%b %d, %Y %I:%M %p %Z'

        dt_object = datetime.strptime(date_string, date_format)

        epoch_timestamp_seconds = int(dt_object.timestamp())

        return epoch_timestamp_seconds * 1000