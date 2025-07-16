package com.ecommercenewgeration.domain.model

data class ProductWithSession (
    val id: String,
    val session: String,
    val listProducts: List<Product>
)