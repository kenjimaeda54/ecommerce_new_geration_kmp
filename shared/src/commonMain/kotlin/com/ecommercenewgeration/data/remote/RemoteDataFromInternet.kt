package com.ecommercenewgeration.data.remote

import com.ecommercenewgeration.data.dto.ProductDto
import com.ecommercenewgeration.domain.model.DataOrException
import com.ecommercenewgeration.infrastructure.remote.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteDataFromInternet(private val ktorApi: KtorApi) : KtorApi by ktorApi {

    suspend fun fetchAllProducts(): DataOrException<List<ProductDto>?, Exception?, Boolean> {
        return try {
            val result = client.get("/api/v1/products")
            DataOrException(result.body(), null, false)
        } catch (exception: Exception) {
            DataOrException(null, exception, false)
        }
    }

}