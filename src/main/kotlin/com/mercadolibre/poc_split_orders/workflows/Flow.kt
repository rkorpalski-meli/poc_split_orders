package com.mercadolibre.poc_split_orders.workflows

import com.mercadolibre.poc_split_orders.entities.Shipment
import org.jeasy.flows.workflow.WorkFlow

interface Flow {
  fun execute(shipment: Shipment)
  fun createFlowInstance(): WorkFlow
}