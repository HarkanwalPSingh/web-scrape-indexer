package com.main.indexer.api

import com.main.indexer.model.Product
import com.main.indexer.service.ProductService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/index")
class ProductController(private val productService: ProductService) {

  @PostMapping("/document")
  fun createProduct(@RequestBody document: Product): Product {
    return productService.save(document)
  }

  @GetMapping("/all")
  fun getAllProducts(): List<Product> {
    return productService.findAll()
  }

  @GetMapping("/document/{id}")
  fun getProduct(@PathVariable id: String): Product? {
    return productService.findById(id)
  }

  @DeleteMapping("/document/{id}")
  fun deleteProduct(@PathVariable id: String) {
    return productService.deleteById(id)
  }

  @DeleteMapping("{id}")
  fun deleteAllProducts(@PathVariable id: String) {
    return productService.deleteAllByIndex(id)
  }

  @PostMapping("/document/search")
  fun findProduct(@RequestBody searchRequest: SearchProductRequest): List<Product> {
    return productService.findProduct(searchRequest.price, searchRequest.description)
  }
}

class SearchProductRequest(
  val price: Double = 0.0,
  val description: String = ""
)