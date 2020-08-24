package com.mercadolibre.poc_split_orders.exceptions

class ApiException(open val errorCode: String, override val message: String) : RuntimeException(message)
