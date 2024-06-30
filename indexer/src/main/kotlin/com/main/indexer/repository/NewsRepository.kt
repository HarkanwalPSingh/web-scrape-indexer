package com.main.indexer.repository

import com.main.indexer.model.NewsItem
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface NewsRepository: ElasticsearchRepository<NewsItem, String> {

  fun findNewsItemsByHeadlinesContainingIgnoreCase(headlines: String): List<NewsItem>
}