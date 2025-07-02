package com.ecommercenewgeration.domain.repository

import com.ecommercenewgeration.domain.model.DataOrException
import com.ecommercenewgeration.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
     fun getProducts(): Flow<DataOrException<List<Product>?,Exception?,Boolean>>
}