package com.ecommercenewgeration.android.ui.screens

import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ecommercenewgeration.domain.model.Product
import com.ecommercenewgeration.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen() {
    val homeViewModel = viewModel<HomeViewModel>()
    val products = homeViewModel.products.collectAsState()

    products.value.data?.let { data ->
        CompositionLocalProvider(LocalOverscrollFactory provides null) {
            LazyColumn {
                items(
                    items = data,
                    key = { data -> data.id }
                ) { product ->
                    Text(product.title)
                }

            }
        }

    }
    products.value.exception?.let { exception ->
        Text("Error")
    }
}