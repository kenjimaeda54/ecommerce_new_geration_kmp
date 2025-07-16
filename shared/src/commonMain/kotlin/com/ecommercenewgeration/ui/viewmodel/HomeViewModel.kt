package com.ecommercenewgeration.ui.viewmodel

import com.ecommercenewgeration.domain.model.DataOrException
import com.ecommercenewgeration.domain.model.ProductWithSession
import com.ecommercenewgeration.domain.usecase.GetProductByCategoryUseCase
import com.ecommercenewgeration.util.CoroutineViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : KoinComponent, CoroutineViewModel() {
    private val getProductUseCase by inject<GetProductByCategoryUseCase>()
    private val _products = MutableStateFlow(
        DataOrException<List<ProductWithSession>?, Throwable?, Boolean>(
            null,
            null,
            true
        )
    )
    val products: StateFlow<DataOrException<List<ProductWithSession>?, Throwable?, Boolean>> =
        _products.asStateFlow()

    init {
        getProductByCategory()
    }

    private fun getProductByCategory() {
        scope.launch {
          getProductUseCase()
              .catch { exception ->
                  _products.value = DataOrException(
                      data = null,
                      exception = exception,
                      isLoading = false
                  )
              }
              .collect { collect ->
                  _products.value = DataOrException(
                      data = collect.data,
                      exception = null,
                      isLoading = false
                  )
              }
        }

    }

}