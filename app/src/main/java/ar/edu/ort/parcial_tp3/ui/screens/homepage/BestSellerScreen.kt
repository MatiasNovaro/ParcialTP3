package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeCard
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.Product


@Composable
fun BestSellerScreen(
    products: List<Product>,
    onAddToCart: (Product) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(products.chunked(2)) { rowProducts ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                for (product in rowProducts) {
                    HomeCard(
                        imageUrl = product.imageUrl,
                        title = product.name,
                        price = product.price,
                        onAddToCart = { onAddToCart(product) },
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                // Relleno si es una fila impar
                if (rowProducts.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
val sampleProducts = listOf(
    Product(
        imageUrl = "product_image", // Usa tu recurso drawable
        name = "Auriculares Bluetooth Inalámbricos",
        price = "$25.99",
        id = 2,
        description = "Descripcion"
    ),
    Product(
        imageUrl = "product_image", // Usa tu recurso drawable
        name = "Auriculares Bluetooth Inalámbricos",
        price = "$25.99",
        id = 2,
        description = "Descripcion"
    ),
    Product(
        imageUrl = "product_image", // Usa tu recurso drawable
        name = "Auriculares Bluetooth Inalámbricos",
        price = "$25.99",
        id = 2,
        description = "Descripcion"
    ),
    Product(
        imageUrl = "product_image", // Usa tu recurso drawable
        name = "Auriculares Bluetooth Inalámbricos",
        price = "$25.99",
        id = 2,
        description = "Descripcion"
    ),
    Product(
        imageUrl = "product_image", // Usa tu recurso drawable
        name = "Auriculares Bluetooth Inalámbricos",
        price = "$25.99",
        id = 2,
        description = "Descripcion"
    ),
    Product(
        imageUrl = "product_image", // Usa tu recurso drawable
        name = "Auriculares Bluetooth Inalámbricos",
        price = "$25.99",
        id = 2,
        description = "Descripcion"
    ),
    Product(
        imageUrl = "product_image", // Usa tu recurso drawable
        name = "Auriculares Bluetooth Inalámbricos",
        price = "$25.99",
        id = 2,
        description = "Descripcion"
    ),
    Product(
        imageUrl = "product_image", // Usa tu recurso drawable
        name = "Auriculares Bluetooth Inalámbricos",
        price = "$25.99",
        id = 2,
        description = "Descripcion"
    ),

)


@Preview
@Composable
fun ProductGridPreview(){
    BestSellerScreen(
        products = sampleProducts,
        onAddToCart = {}
    )
}