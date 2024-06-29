package com.main.indexer.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean

import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

  @Bean
  fun customOpenAPI(): OpenAPI = OpenAPI()
    .info(
      Info()
        .title("Indexer APIs")
        .version("1.0")
        .description("Example Elastic Search Indexer")
    )
}