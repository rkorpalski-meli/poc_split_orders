package com.mercadolibre.poc_split_orders

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.AbstractEnvironment
import java.util.*

@SpringBootApplication(scanBasePackages=["com.mercadolibre"])
class Application

fun main(args: Array<String>) {
  setupEnv()
  runApplication<Application>(*args)
}

private fun setupEnv() {
  TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
  System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, getCurrentProfile())
}

private fun getCurrentProfile(): String {
  val scope = System.getenv("SCOPE") ?: "development"
  return when {
    scope.contains("stage", true) -> "stage"
    scope.contains("production", true) -> "production"
    else -> scope
  }
}