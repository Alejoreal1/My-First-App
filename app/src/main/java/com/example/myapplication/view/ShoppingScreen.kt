package com.example.myapplication.view




import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
// Asegúrate de que estos imports no estén en rojo.
// Si lo están, bórralos y presiona Alt+Enter sobre InputSuperior y ProductCard para re-importarlos.
import com.example.myapplication.components.InputSuperior
import com.example.myapplication.components.ProductCard
import com.example.myapplication.models.Product

@Composable
fun ShoppingScreen() {
    var presupuestoInput by remember { mutableStateOf("") }

    val listaInicial = listOf(
        Product(1, "Pan coco", 500.0,0, "https://img-global.cpcdn.com/recipes/33af8febc90473a5/1200x630cq80/photo.jpg"),
        Product(2, "Empanada", 2000.0,0, "https://www.cocina-colombiana.com/base/stock/Recipe/empanadas-colombianas/empanadas-colombianas_web.jpg.webp"),
        Product(3, "Bueñuelos", 1000.0,0, "https://www.elespectador.com/resizer/v2/DLEQPHYNTZFGVEPDJ2VQDANSDI.jpg?auth=d0fdad70a96e669e4afca2d38102d0762eab438fab7847070e5fcf3b014fb25e&width=920&height=613&smart=true&quality=60"),
        Product(4, "pandebono", 1000.0,0, "https://cdn.colombia.com/gastronomia/2011/08/05/pandebono-1638.gif"),
        Product(5,"cuajadas",2000.0,0, "https://cdn.colombia.com/gastronomia/2017/05/02/cuajada-casera-3101.jpg"),
        Product(6,"Pan",3000.0,0, "https://i.blogs.es/512fb8/pan_comun/650_1200.jpg"),
        Product(7,"Arepa",800.0,0, "https://imagenes2.eltiempo.com/files/image_1200_535/uploads/2024/04/04/660edbab9e0fd.jpeg"),
        Product(8,"Arepa rellena mixta",5000.0,0,"https://cloudfront-us-east-1.images.arcpublishing.com/elespectador/ZVT27NKSWFGLDCRYQ4KVQ2ZHMU.jpg") ,
        Product(9,"Arepa rellena pollo",5000.0,0,"https://polloseldorado.co/wp-content/uploads/2024/03/Arepa-de-Pollo-1024x536.jpg"),
        Product(10,"Tamal",5000.0,0,"https://delishglobe.com/wp-content/uploads/2025/01/Tamal-Tolimense-Stuffed-Corn-Dough.png"),
        Product(11,"Arepa con huevo",3500.0,0,"https://molipeter.com/wp-content/uploads/2023/06/arepa-huevo-luruaco.webp"),
        Product(12,"Perico",4000.0,0,"https://huevosoro.com/wp-content/uploads/2024/10/Huevos-Pericos.jpg"),
        Product(13,"cafe",1500.0,0,"https://www.elespectador.com/resizer/v2/ZKHWFCF5SNFNBBTPH23PWUUBVE.jpg?auth=b1d892c92c629e7f49c57398bd00d2915b446ab9a0ffc8e6d303c87f4844befb&width=910&height=606&smart=true&quality=70"),
        Product(14,"cafe con leche",2000.0,0,"https://i.blogs.es/421374/cafe-con-leche2/450_1000.jpg"),
        Product(15,"Gaseosa",4000.0,0,"https://huevosoro.com/wp-content/uploads/2024/10/Huevos-Pericos.jpg"),
        Product(16,"Agua",1500.0,0,"https://exitocol.vtexassets.com/arquivos/ids/28929765/Agua-Pura-Tratada-Exito-X-1100-ml-403042_a.jpg?v=638859482257000000"),
        Product(17,"cafe con leche",2000.0,0,"https://www.splenda.com/wp-content/themes/bistrotheme/assets/recipe-images/make-ahead-hot-cocoa-mix.jpg")


    )

    val products = remember { mutableStateListOf(*listaInicial.toTypedArray()) }

    val presupuestoLimite = presupuestoInput.toDoubleOrNull() ?: 0.0
    val totalActual = products.sumOf { it.price * it.quantity }
    val cantidadItems = products.sumOf { it.quantity }

    val colorPrecio = if (totalActual <= presupuestoLimite) Color(0xFF4CAF50) else Color.Red

    Scaffold(
        topBar = {
            InputSuperior(
                valor = presupuestoInput,
                onValorChange = { presupuestoInput = it }
            )
        },
        bottomBar = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Productos seleccionados: $cantidadItems")
                    Row {
                        Text(text = "Total a pagar: ", fontWeight = FontWeight.Bold)
                        Text(
                            text = "$ ${totalActual.toInt()}",
                            color = colorPrecio,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onAdd = {
                        val index = products.indexOf(product)
                        products[index] = product.copy(quantity = product.quantity + 1)
                    },
                    onRemove = {
                        if (product.quantity > 0) {
                            val index = products.indexOf(product)
                            products[index] = product.copy(quantity = product.quantity - 1)
                        }
                    }
                )
            }
        }
    }
}