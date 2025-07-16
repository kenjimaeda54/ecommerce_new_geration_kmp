package com.ecommercenewgeration.android.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.LocalOverscrollFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ecommercenewgeration.android.ui.screens.view.ItemProduct
import com.ecommercenewgeration.android.ui.theme.FinancesApplicationTheme
import com.ecommercenewgeration.android.util.ThemePreviews
import com.ecommercenewgeration.domain.model.Product
import com.ecommercenewgeration.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen() {
    val homeViewModel = viewModel<HomeViewModel>()
    val products = homeViewModel.products.collectAsState()

    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primary
            )
            .padding(horizontal = 20.dp, vertical = 25.dp)
            .safeDrawingPadding()
    ) {

        products.value.data?.let { data ->

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                items(
                    items = data,
                    key = { data -> data.id }
                ) { category ->
                    Text(
                        modifier = Modifier.padding(
                            bottom = 10.dp
                        ),
                        text = category.session,
                        style = MaterialTheme.typography.titleLarge,
                        color =
                            MaterialTheme.colorScheme.secondary
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(category.listProducts, key = { data -> data.id }) { product ->
                            ItemProduct(product)
                        }
                    }

                }
            }

        }

    }
    products.value.exception?.let { exception ->
        Text("Error")
    }

}


@Composable
fun RowItems() {


}


@Composable
@ThemePreviews
fun HomePreview() {
    FinancesApplicationTheme {
        RowItems()
    }
}


