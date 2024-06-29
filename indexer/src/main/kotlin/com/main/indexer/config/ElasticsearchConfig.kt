package com.main.indexer.config

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.rest_client.RestClientTransport
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories

const val SERVER_URL = "http://localhost:9200"

@Configuration
@EnableElasticsearchRepositories(basePackages = ["com.main.indexer.repository"])
class ElasticsearchConfig {

  @Bean
  fun client(): RestClient =
    RestClient
      .builder(HttpHost.create(SERVER_URL))
      .build()

  @Bean
  fun transport(): RestClientTransport =
    RestClientTransport(client(), JacksonJsonpMapper())

  @Bean
  fun esClient(): ElasticsearchClient =
    ElasticsearchClient(transport())
}