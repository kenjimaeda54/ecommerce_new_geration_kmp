package com.ecommercenewgeration.data.remote

import com.ecommercenewgeration.data.dto.CategoryDto
import com.ecommercenewgeration.data.dto.ProductDto
import com.ecommercenewgeration.domain.model.DataOrException
import com.ecommercenewgeration.infrastructure.remote.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val PARAMETERS_CATEGORY = "categorySlug"

class RemoteDataFromInternet(private val ktorApi: KtorApi) : KtorApi by ktorApi {

    suspend fun fetchAllOrFilterProducts(categorySlug: String? = null): DataOrException<List<ProductDto>?, Exception?, Boolean> {
        return try {
            val result = client.get("/api/v1/products") {
                url {
                    categorySlug?.let { slug ->
                        url.parameters.append(PARAMETERS_CATEGORY,slug)

                    }
                }
            }
            DataOrException(result.body(), null, false)
        } catch (exception: Exception) {
            DataOrException(null, exception, false)
        }
    }

    suspend fun fetchCategories(): DataOrException<List<CategoryDto>?, Exception?, Boolean> {
        return try {
            val result = client.get("/api/v1/categories")
            DataOrException(result.body(), exception = null, isLoading = false)
        } catch (exception: Exception) {
            DataOrException(null, exception = exception, isLoading = false)
        }
    }

}