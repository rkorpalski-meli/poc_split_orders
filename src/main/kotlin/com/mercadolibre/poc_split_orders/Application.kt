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
}
