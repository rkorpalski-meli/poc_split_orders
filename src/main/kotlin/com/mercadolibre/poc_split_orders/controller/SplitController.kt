package com.mercadolibre.poc_split_orders.controller

import com.mercadolibre.poc_split_orders.entities.Shipment
import com.mercadolibre.poc_split_orders.exceptions.ApiException
import com.mercadolibre.poc_split_orders.services.CancelOrderService
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("split")
class SplitController(val cancelOrderService: CancelOrderService) {

  private val logger = LoggerFactory.getLogger("SplitController")

  @RequestMapping("", method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
  fun splitOrder(@RequestBody shipment: Shipment): ResponseEntity<Any> {
    try {
      cancelOrderService.cancelOrder(shipment.id)
    }catch (e: ApiException){
      logger.error(e.message, e)
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR_500).body(e.message)
    }
    return ResponseEntity.ok().build()
  }
}