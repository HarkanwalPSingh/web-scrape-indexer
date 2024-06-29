package com.main.indexer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IndexerApplication

fun main(args: Array<String>) {
  runApplication<IndexerApplication>(*args)
}
