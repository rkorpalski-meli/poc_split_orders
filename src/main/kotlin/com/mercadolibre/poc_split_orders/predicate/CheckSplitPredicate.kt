package com.mercadolibre.poc_split_orders.predicate

import org.jeasy.flows.work.WorkReport
import org.jeasy.flows.work.WorkReportPredicate

class CheckSplitPredicate: WorkReportPredicate {
  override fun apply(workReport: WorkReport): Boolean {
    val ctx = workReport.workContext
    return ctx.get("resultSplit") as Boolean
  }

}