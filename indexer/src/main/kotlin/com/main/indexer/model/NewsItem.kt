package com.main.indexer.model

import jakarta.xml.bind.DatatypeConverter
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import java.security.MessageDigest

@Document(indexName = "news")
class NewsItem(
  val url: String,
  val headlines: String,
  val content: String,
  val timestamp: Long
) {
  @Id val id: String = generateHash(content)

  private fun generateHash(data: String): String {
    val md = MessageDigest.getInstance("SHA-256")
    val hashBytes = md.digest(data.toByteArray(Charsets.UTF_8))
    return DatatypeConverter.printHexBinary(hashBytes)
  }
}