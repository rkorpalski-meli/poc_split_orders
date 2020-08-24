package com.mercadolibre.poc_split_orders.workflows

import com.mercadolibre.conductor.work.UnitOfWorkType
import com.mercadolibre.conductor.workflows.WorkflowExecution
import com.mercadolibre.poc_split_orders.clients.AuthorizationClient
import com.mercadolibre.poc_split_orders.clients.TmsClient
import com.mercadolibre.poc_split_orders.entities.Shipment
import com.mercadolibre.poc_split_orders.works.CancelAuthorizationWork
import com.mercadolibre.poc_split_orders.works.CancelTmsWork
import com.mercadolibre.poc_split_orders.works.CheckSplitWork
import com.mercadolibre.poc_split_orders.works.RepeatableTestWork
import org.jeasy.flows.engine.WorkFlowEngineBuilder
import org.jeasy.flows.work.WorkContext
import org.jeasy.flows.work.WorkReportPredicate
import org.jeasy.flows.workflow.ConditionalFlow.Builder.aNewConditionalFlow
import org.jeasy.flows.workflow.SequentialFlow.Builder.aNewSequentialFlow
import org.jeasy.flows.workflow.ParallelFlow.Builder.aNewParallelFlow
import org.jeasy.flows.workflow.RepeatFlow.Builder.aNewRepeatFlow
import org.jeasy.flows.workflow.WorkFlow
import org.springframework.stereotype.Service
import java.util.concurrent.Executors


@Service
class MLMFlow(val authorizationClient: AuthorizationClient, val tmsClient: TmsClient): Flow {

  override fun createFlowInstance(): WorkFlow {
    val  executorService = Executors.newFixedThreadPool(2)
    return aNewSequentialFlow()
      .execute(aNewConditionalFlow()
        .execute(CheckSplitWork(UnitOfWorkType.NON_REPEATABLE))
        .`when`(WorkReportPredicate.COMPLETED)
        .then(aNewParallelFlow(executorService)
          .execute(CancelAuthorizationWork(UnitOfWorkType.NON_REPEATABLE, authorizationClient),
            CancelTmsWork(UnitOfWorkType.NON_REPEATABLE, tmsClient)).build())
        .build()).then(aNewRepeatFlow().repeat(RepeatableTestWork(UnitOfWorkType.REPEATABLE)).times(3).build())
      .build()
  }

  override fun execute(shipment: Shipment) {
      val workFlowEngine = WorkFlowEngineBuilder.aNewWorkFlowEngine().build()
      val flowMLM = createFlowInstance()
      val workFlowExecution = WorkflowExecution("start", flowMLM)
      val workFlowContext = WorkContext()
      workFlowContext.put("shipment", shipment)
      val workReport = workFlowExecution.execute("start", workFlowContext, workFlowEngine)
      if(workReport.error != null) throw workReport.error
  }
}