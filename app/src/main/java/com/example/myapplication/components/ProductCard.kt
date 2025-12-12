// com.example.myapplication.components/ProductCard.kt

package com.example.myapplication.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

import coil.compose.AsyncImage
import com.example.myapplication.models.Product


@Composable
fun ProductCard(
    product: Product,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        // ... otros parámetros de Card
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {


            AsyncImage(
                model = product.imageUrl, // La URL del producto
                contentDescription = product.name,
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Columna de texto y controles
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "$ ${product.price.toInt()}", style = MaterialTheme.typography.bodySmall)
            }

            // Columna de botones de cantidad
            Column(horizontalAlignment = androidx.compose.ui.Alignment.End) {

                Text("Cantidad: ${product.quantity}")
                Spacer(Modifier.height(8.dp))
                Row {
                    Button(onClick = onRemove, contentPadding = PaddingValues(4.dp)) {
                        Text("-")
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = onAdd, contentPadding = PaddingValues(4.dp)) {
                        Text("+")
                    }
                }
            }
        }
    }
}