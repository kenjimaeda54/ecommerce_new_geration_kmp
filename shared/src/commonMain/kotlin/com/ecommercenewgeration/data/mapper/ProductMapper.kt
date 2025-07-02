package com.ecommercenewgeration.data.mapper

import com.ecommercenewgeration.data.dto.CategoryDto
import com.ecommercenewgeration.data.dto.ProductDto
import com.ecommercenewgeration.domain.model.Category
import com.ecommercenewgeration.domain.model.Product


fun ProductDto.toDomain() = Product(
    id = this.id,
    title = this.title,
    slug = this.slug,
    price = this.price,
    description = this.description,
    category = setCategory(this.category),
    images = this.images
)

fun setCategory(categoryDto: CategoryDto) = Category(
    id = categoryDto.id,
    name = categoryDto.name,
    image = categoryDto.image,
    slug = categoryDto.slug
)