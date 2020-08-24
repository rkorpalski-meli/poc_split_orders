package com.mercadolibre.poc_split_orders.works

import com.mercadolibre.conductor.work.UnitOfWork
import com.mercadolibre.conductor.work.UnitOfWorkType
import com.mercadolibre.poc_split_orders.clients.AuthorizationClient
import com.mercadolibre.poc_split_orders.entities.Shipment
import org.jeasy.flows.work.WorkContext

class CancelAuthorizationWork(unitOfWorkType: UnitOfWorkType?, val authorizationClient: AuthorizationClient) : UnitOfWork(unitOfWorkType) {

  override fun execute(ctx: WorkContext) {
    val shipment = ctx.get("shipment") as Shipment
    authorizationClient.cancelAuthorization(shipment.id)
  }
}