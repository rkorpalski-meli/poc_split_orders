package com.mercadolibre.poc_split_orders.clients

import com.mercadolibre.fbm.clients.ShipmentClientService
import com.mercadolibre.poc_split_orders.entities.Shipment
import com.mercadolibre.poc_split_orders.entities.Source
import com.mercadolibre.poc_split_orders.exceptions.ApiException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class ShipmentClient(private val shipmentClientService: ShipmentClientService) {

  fun getShipment(id: String): Shipment {
    try {
      //shipmentClientService.get(id.toLong(), Shipment::class.java)
      return Shipment(id, Source("MLM"))
    } catch (e: Exception) {
      throw ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), e.message!!)
    }
  }
}
