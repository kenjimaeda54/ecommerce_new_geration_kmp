package com.ecommercenewgeration.data.mapper

import com.ecommercenewgeration.data.dto.CategoryDto
import com.ecommercenewgeration.domain.model.Category


fun CategoryDto.toDomain() = Category(
    id = this.id,
    name = this.name,
    image = this.image,
    slug = this.slug
)