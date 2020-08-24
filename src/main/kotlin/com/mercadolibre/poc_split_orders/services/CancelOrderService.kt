package com.mercadolibre.poc_split_orders.services

import com.mercadolibre.poc_split_orders.clients.ShipmentClient
import com.mercadolibre.poc_split_orders.workflows.MLMFlow
import org.springframework.stereotype.Service

@Service
class CancelOrderService(private val shipmentClient: ShipmentClient, private val mlmFlow: MLMFlow) {

  fun cancelOrder(shipmentId: String) {
    val shipment = shipmentClient.getShipment(shipmentId)

    when (shipment.source!!.siteid) {
      "MLM" -> mlmFlow.execute(shipment)
    }
  }
}