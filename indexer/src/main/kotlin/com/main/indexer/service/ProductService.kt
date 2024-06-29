package com.main.indexer.service

import com.main.indexer.model.Product
import com.main.indexer.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
  fun save(product: Product): Product {
    return productRepository.save(product)
  }

  fun findAll(): List<Product> {
    return productRepository.findAll().toList()
  }

  fun findById(id: String): Product? {
    return productRepository.findById(id).orElse(null)
  }

  fun deleteById(id: String) {
    return productRepository.deleteById(id)
  }

  fun deleteAllByIndex(index: String) {
    return productRepository.deleteAll()
  }

  fun findProduct(price: Double, description: String): List<Product> {
    return productRepository.findProductByPriceGreaterThanEqualAndDescriptionContainsIgnoreCase(price, description)
  }

  fun findByDescription(description: String): List<Product> {
    return productRepository.findProductByDescriptionContainingIgnoreCase(description)
  }

  fun findByPriceGreaterThan(price: Double): List<Product> {
    return productRepository.findProductByPriceGreaterThanEqual(price)
  }
}