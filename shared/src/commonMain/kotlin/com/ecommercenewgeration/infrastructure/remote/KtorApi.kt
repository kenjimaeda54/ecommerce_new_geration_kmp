package com.ecommercenewgeration.infrastructure.remote

import io.ktor.client.HttpClient

interface KtorApi {
    val client: HttpClient
}