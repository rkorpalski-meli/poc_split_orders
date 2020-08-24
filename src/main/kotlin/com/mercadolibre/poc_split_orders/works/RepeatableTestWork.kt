package com.mercadolibre.poc_split_orders.works

import com.mercadolibre.conductor.work.UnitOfWork
import com.mercadolibre.conductor.work.UnitOfWorkType
import org.jeasy.flows.work.WorkContext

class RepeatableTestWork(unitOfWorkType: UnitOfWorkType) : UnitOfWork(unitOfWorkType) {
  override fun execute(ctx: WorkContext) {
    println("Test of repeatable work")
  }
}