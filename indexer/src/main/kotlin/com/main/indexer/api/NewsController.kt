package com.main.indexer.api

import com.main.indexer.model.NewsItem
import com.main.indexer.service.NewsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/news-indexer")
class NewsController(private val newsService: NewsService) {

  @PostMapping("/index")
  fun addNews(@RequestBody newsItem: NewsItem): NewsItem {
    return newsService.save(newsItem)
  }

  @GetMapping("/{id}")
  fun getNews(@PathVariable id: String): NewsItem? {
    return newsService.findById(id)
  }

  @GetMapping("/all")
  fun getAllNews(): List<NewsItem> {
    return newsService.findAll()
  }

  @GetMapping("/search")
  fun searchNewsByHeadlines(@RequestParam headline: String): List<NewsItem> {
    return newsService.searchHeadline(headline)
  }
}