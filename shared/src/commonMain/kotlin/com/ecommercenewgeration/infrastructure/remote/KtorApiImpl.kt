package com.ecommercenewgeration.infrastructure.remote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val API_URL = "https://api.escuelajs.co"

class KtorApiImpl : KtorApi {

    override val client: HttpClient = HttpClient {
        defaultRequest {
            url(API_URL)
            url {
                protocol = URLProtocol.HTTPS
            }
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            })
        }
    }

}