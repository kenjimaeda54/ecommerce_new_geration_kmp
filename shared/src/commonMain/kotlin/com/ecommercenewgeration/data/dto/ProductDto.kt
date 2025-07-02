package com.ecommercenewgeration.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val slug: String,
    val price: Int,
    val description: String,
    val category: CategoryDto,
    val images: List<String>

)

@Serializable
data class CategoryDto(
    val id: Int,
    val name: String,
    val image: String,
    val slug: String
)