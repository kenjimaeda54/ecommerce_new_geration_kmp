package com.ecommercenewgeration.domain.repository

import com.ecommercenewgeration.domain.model.Category
import com.ecommercenewgeration.domain.model.DataOrException
import com.ecommercenewgeration.domain.model.Product
import com.ecommercenewgeration.domain.model.ProductWithSession
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(): Flow<DataOrException<List<Product>?, Exception?, Boolean>>

    fun getProductByCategory(): Flow<DataOrException<List<ProductWithSession>?, Exception?, Boolean>>

    fun getCategories(): Flow<DataOrException<List<Category>?, Exception?, Boolean>>
}