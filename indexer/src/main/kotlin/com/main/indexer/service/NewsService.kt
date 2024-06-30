package com.main.indexer.service

import com.main.indexer.model.NewsItem
import com.main.indexer.repository.NewsRepository
import org.springframework.stereotype.Service

@Service
class NewsService(private val newsRepository: NewsRepository) {
  fun save(newsItem: NewsItem): NewsItem {
    return newsRepository.save(newsItem)
  }

  fun findAll(): List<NewsItem> {
    return newsRepository.findAll().toList()
  }

  fun findById(id: String): NewsItem? {
    return newsRepository.findById(id).orElse(null)
  }

  fun searchHeadline(headlines: String): List<NewsItem> {
    return newsRepository.findNewsItemsByHeadlinesContainingIgnoreCase(headlines)
  }
}