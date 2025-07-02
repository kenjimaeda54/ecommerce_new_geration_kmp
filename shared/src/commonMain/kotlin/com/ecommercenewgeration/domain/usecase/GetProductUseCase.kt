package com.ecommercenewgeration.domain.usecase

import com.ecommercenewgeration.domain.model.DataOrException
import com.ecommercenewgeration.domain.model.Product
import com.ecommercenewgeration.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetProductUseCase {
    operator fun invoke(): Flow<DataOrException<List<Product>?, Exception?, Boolean>>
}

class GetProductUseCaseImpl(
    private val productRepository: ProductRepository
) : GetProductUseCase {

    override fun invoke(): Flow<DataOrException<List<Product>?, Exception?, Boolean>> =
        productRepository.getProducts()

}