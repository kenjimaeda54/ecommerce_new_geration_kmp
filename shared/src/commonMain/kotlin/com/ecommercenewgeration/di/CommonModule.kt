package com.ecommercenewgeration.di

import com.ecommercenewgeration.data.remote.RemoteDataFromInternet
import com.ecommercenewgeration.data.repository.ProductRepositoryImpl
import com.ecommercenewgeration.domain.repository.ProductRepository
import com.ecommercenewgeration.domain.usecase.GetProductByCategoryUseCase
import com.ecommercenewgeration.domain.usecase.GetProductByCategoryUseCaseImpl
import com.ecommercenewgeration.infrastructure.remote.KtorApi
import com.ecommercenewgeration.infrastructure.remote.KtorApiImpl
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        client,
        useCase,
        repository
    )
}


private val client = module {
    single { RemoteDataFromInternet(get()) }
    factory<KtorApi> { KtorApiImpl() }
}

private val useCase = module {
    single<GetProductByCategoryUseCase> { GetProductByCategoryUseCaseImpl(get()) }
}

private val repository = module {
    single<ProductRepository> { ProductRepositoryImpl() }
}

fun initKoin() = initKoin {  }



