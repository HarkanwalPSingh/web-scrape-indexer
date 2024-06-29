package com.main.indexer.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.util.UUID

@Document(indexName = "products")
data class Product(
  @Id val id: UUID = UUID.randomUUID(),
  val description: String,
  val price: Double,
  val company: String,
  val timestamp: Long = System.currentTimeMillis()
)

