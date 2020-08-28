package com.mercadolibre.poc_split_orders.hook

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.mercadolibre.conductor.hooks.Hook
import com.mercadolibre.conductor.json.Serializable
import com.mercadolibre.conductor.work.UnitOfWork
import org.jeasy.flows.work.WorkContext
import java.time.LocalDateTime

class StartWorkHook: Hook{
  override fun serialize(p0: JsonGenerator?) {
    TODO("Not yet implemented")
  }

  override fun deserialize(p0: JsonParser?, p1: DeserializationContext?, p2: JsonNode?): Serializable {
    TODO("Not yet implemented")
  }

  override fun perform(ctx: WorkContext, work: UnitOfWork) {
    val start = LocalDateTime.now()
    println("Starting CancelAuthorization at $start")
    ctx.put("startTime", start)
  }

}