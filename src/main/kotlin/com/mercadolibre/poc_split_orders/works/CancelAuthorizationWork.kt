package com.mercadolibre.poc_split_orders.works

import com.mercadolibre.conductor.work.UnitOfWork
import com.mercadolibre.conductor.work.UnitOfWorkType
import com.mercadolibre.poc_split_orders.clients.AuthorizationClient
import com.mercadolibre.poc_split_orders.entities.Shipment
import com.mercadolibre.poc_split_orders.hook.EndWorkHook
import com.mercadolibre.poc_split_orders.hook.StartWorkHook
import org.jeasy.flows.work.WorkContext

class CancelAuthorizationWork(unitOfWorkType: UnitOfWorkType?, val authorizationClient: AuthorizationClient) : UnitOfWork(unitOfWorkType) {

  init {
      this.onReaching(StartWorkHook())
      this.onLeaving(EndWorkHook())
  }

  override fun execute(ctx: WorkContext) {
    val shipment = ctx.get("shipment") as Shipment
    authorizationClient.cancelAuthorization(shipment.id)
  }
}