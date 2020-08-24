package com.mercadolibre.poc_split_orders.works

import com.mercadolibre.conductor.work.UnitOfWork
import com.mercadolibre.conductor.work.UnitOfWorkType
import com.mercadolibre.poc_split_orders.entities.Shipment
import com.mercadolibre.poc_split_orders.exceptions.ApiException
import org.jeasy.flows.work.WorkContext
import org.springframework.http.HttpStatus

class CheckSplitWork(unitOfWorkType: UnitOfWorkType) : UnitOfWork(unitOfWorkType) {
  override fun execute(ctx: WorkContext) {
    val shipment = ctx.get("shipment") as Shipment
    if(shipment?.id != null) {
      println("Executing Check split")
    } else {
      throw ApiException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Invalid split")
    }
  }
}