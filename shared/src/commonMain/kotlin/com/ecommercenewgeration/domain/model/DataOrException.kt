package com.ecommercenewgeration.domain.model

data class DataOrException<T,Exception,Boolean>(
    val data: T? = null,
    val exception: Exception,
    val isLoading: Boolean
)