package com.ecommercenewgeration.android.ui.screens.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ecommercenewgeration.domain.model.Product


@Composable
fun ItemProduct(product: Product) {
    Column(
        modifier = Modifier.width(250.dp)
    ) {

        Card(
            modifier = Modifier
                .height(250.dp).fillMaxWidth(),
            shape = RoundedCornerShape(5.dp),
            elevation = CardDefaults.elevatedCardElevation(
                4.dp
            )
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(product.images.firstOrNull() ?: "https://github.com/kenjimaeda54.png")
                    .crossfade(true)
                    .build(),

                contentDescription = "Image product",
                contentScale = ContentScale.FillBounds

            )

        }
        Row(
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    product.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    product.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    "${product.price}",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Icon(
                modifier = Modifier.align(Alignment.Bottom),
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Icon favorite",
                tint = MaterialTheme.colorScheme.onSurfaceVariant

            )

        }


    }

}