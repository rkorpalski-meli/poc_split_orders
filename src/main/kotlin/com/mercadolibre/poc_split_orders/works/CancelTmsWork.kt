package com.mercadolibre.poc_split_orders.works

import com.mercadolibre.conductor.work.UnitOfWork
import com.mercadolibre.conductor.work.UnitOfWorkType
import com.mercadolibre.poc_split_orders.clients.TmsClient
import com.mercadolibre.poc_split_orders.entities.Shipment
import org.jeasy.flows.work.WorkContext

class CancelTmsWork(unitOfWorkType: UnitOfWorkType?, val tmsClient: TmsClient) : UnitOfWork(unitOfWorkType) {
  override fun execute(ctx: WorkContext) {
    val shipment = ctx.get("shipment") as Shipment
    tmsClient.cancelTms(shipment.id)
  }

}