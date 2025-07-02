package com.ecommercenewgeration.data.repository

import com.ecommercenewgeration.data.mapper.toDomain
import com.ecommercenewgeration.data.remote.RemoteDataFromInternet
import com.ecommercenewgeration.domain.model.DataOrException
import com.ecommercenewgeration.domain.model.Product
import com.ecommercenewgeration.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductRepositoryImpl : ProductRepository, KoinComponent {
    private val remoteDataFromInternet by inject<RemoteDataFromInternet>()

    override fun getProducts(): Flow<DataOrException<List<Product>?, Exception?, Boolean>> = flow {
        val result = remoteDataFromInternet.fetchAllProducts()
        emit(
            DataOrException<List<Product>?,Exception?,Boolean>(
                data = result.data?.map { it.toDomain() } ,
                exception = result.exception,
                isLoading = result.isLoading
            )
        )
    }.flowOn(Dispatchers.IO)


}