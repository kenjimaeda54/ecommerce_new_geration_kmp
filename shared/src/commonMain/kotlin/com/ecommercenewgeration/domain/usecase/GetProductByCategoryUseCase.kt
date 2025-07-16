package com.ecommercenewgeration.domain.usecase

import com.ecommercenewgeration.domain.model.DataOrException
import com.ecommercenewgeration.domain.model.ProductWithSession
import com.ecommercenewgeration.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

interface GetProductByCategoryUseCase {
    operator fun invoke(): Flow<DataOrException<List<ProductWithSession>?, Exception?, Boolean>>
}

class GetProductByCategoryUseCaseImpl(
    private val productRepository: ProductRepository
) : GetProductByCategoryUseCase {

    override fun invoke(): Flow<DataOrException<List<ProductWithSession>?, Exception?, Boolean>> =
        productRepository.getProductByCategory()

}