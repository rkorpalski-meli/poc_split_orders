package com.mercadolibre.poc_split_orders.clients

import com.mercadolibre.poc_split_orders.exceptions.ApiException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.lang.Exception
import java.time.LocalDateTime

@Service
class TmsClient {

  fun cancelTms(shipmentID: String) {
    try {
      println(LocalDateTime.now().toString() + " Cancel TMS Order")
    } catch (e: Exception) {
      throw ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.message!!)
    }
  }
}
