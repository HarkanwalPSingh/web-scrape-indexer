plugins {
  id("org.springframework.boot") version "3.3.1"
  id("io.spring.dependency-management") version "1.1.5"
  kotlin("jvm") version "1.9.24"
  kotlin("plugin.spring") version "1.9.24"
}

group = "com.main"
version = "0.0.1-SNAPSHOT"

java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(17)
  }
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-amqp")
  implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  developmentOnly("org.springframework.boot:spring-boot-docker-compose")
  runtimeOnly("org.postgresql:postgresql")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testImplementation("org.springframework.amqp:spring-rabbit-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
  compilerOptions {
    freeCompilerArgs.addAll("-Xjsr305=strict")
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}