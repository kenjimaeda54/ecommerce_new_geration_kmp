package com.ecommercenewgeration.data.repository

import com.ecommercenewgeration.data.dto.CategoryDto
import com.ecommercenewgeration.data.mapper.toDomain
import com.ecommercenewgeration.data.remote.RemoteDataFromInternet
import com.ecommercenewgeration.domain.model.Category
import com.ecommercenewgeration.domain.model.DataOrException
import com.ecommercenewgeration.domain.model.Product
import com.ecommercenewgeration.domain.model.ProductWithSession
import com.ecommercenewgeration.domain.repository.ProductRepository
import io.ktor.util.Digest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ProductRepositoryImpl : ProductRepository, KoinComponent {
    private val remoteDataFromInternet by inject<RemoteDataFromInternet>()

    override fun getAllProducts(): Flow<DataOrException<List<Product>?, Exception?, Boolean>> =
        flow {
            val result = remoteDataFromInternet.fetchAllOrFilterProducts()
            emit(
                DataOrException<List<Product>?, Exception?, Boolean>(
                    data = result.data?.map { it.toDomain() },
                    exception = result.exception,
                    isLoading = result.isLoading
                )
            )
        }.flowOn(Dispatchers.IO)

    override fun getProductByCategory(): Flow<DataOrException<List<ProductWithSession>?, Exception?, Boolean>> =
        flow {
            val listCategories = remoteDataFromInternet.fetchCategories()

            if (listCategories.data.isNullOrEmpty()) {
                emit(
                    DataOrException<List<ProductWithSession>?, Exception?, Boolean>(
                        emptyList(),
                        null,
                        false
                    )
                )
                return@flow
            }

            val productsWithSession = returnProductsWithSession(listCategories.data)

            emit(
                DataOrException<List<ProductWithSession>?, Exception?, Boolean>(
                    data = productsWithSession,
                    exception = null,
                    isLoading = false
                )
            )

        }.flowOn(Dispatchers.IO)

    private suspend fun returnProductsWithSession(listCategories: List<CategoryDto>): List<ProductWithSession> {
        return coroutineScope {
            listCategories.map { category ->
                async(Dispatchers.IO) {
                    val product = remoteDataFromInternet.fetchAllOrFilterProducts(category.slug)

                    if (!product.data.isNullOrEmpty()) {
                        ProductWithSession(
                            id = "${category.id} + ${category.slug} ",
                            session = category.name,
                            listProducts = product.data.map { it.toDomain() }
                        )
                    } else {
                        ProductWithSession(
                            id = "${category.id} + ${category.slug} ",
                            session = "",
                            listProducts = emptyList()
                        )
                    }


                }
            }.awaitAll()
        }

    }

    override fun getCategories(): Flow<DataOrException<List<Category>?, Exception?, Boolean>> =
        flow {
            val result = remoteDataFromInternet.fetchCategories()
            emit(
                DataOrException<List<Category>?, Exception?, Boolean>(
                    data = result.data?.map { it.toDomain() },
                    exception = result.exception,
                    isLoading = result.isLoading
                )
            )
        }.flowOn(Dispatchers.IO)


}