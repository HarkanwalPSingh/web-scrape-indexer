package com.main.indexer.repository

import com.main.indexer.model.Product
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: ElasticsearchRepository<Product, String> {
  fun findProductByDescriptionContainingIgnoreCase(description: String): List<Product>

  fun findProductByPriceGreaterThanEqual(price: Double): List<Product>

  fun findProductByPriceGreaterThanEqualAndDescriptionContainsIgnoreCase(price: Double, description: String): List<Product>
}