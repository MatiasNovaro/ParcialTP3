package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.CustomSwipeableProductCard
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeCard



// You'll likely need a CartItem data class if you track quantity per item
data class CartItem(
    val product: Product,
    var quantity: Int // Mutable quantity for each item in the cart
)
@Composable
fun CartScreen(
    onBackClick: () -> Unit,
    // Typically, you'd pass a ViewModel or a list of CartItems here
    // For preview, we'll use a mutableStateListOf
    initialCartItems: List<Product> = emptyList() // Using Product directly for simplicity
) {
    // In a real app, this would come from a ViewModel or a global state manager
    val cartItems = remember { mutableStateListOf(*initialCartItems.toTypedArray()) }

    // Calculate totals - simplified for demonstration, use proper number types for real app
    val itemCount = cartItems.size
    val subtotal = cartItems.sumOf { it.price }
    val taxRate = 0.05 // 5% tax
    val tax = subtotal * taxRate
    val total = subtotal + tax
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Apply padding from Scaffold
            .background(Color(0xFFF0F0F0)) // Light grey background for the entire screen content
    ) {
        // List of Cart Items
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Takes up remaining space
                .padding(horizontal = 20.dp, vertical = 10.dp), // Padding for the list
            verticalArrangement = Arrangement.spacedBy(10.dp) // Spacing between cards
        ) {
            items(cartItems, key = { it.id }) { item ->
                CustomSwipeableProductCard(
                    product = item,
                    onDelete = { productToDelete ->
                        // Remove item from the list when delete is confirmed
                        cartItems.remove(productToDelete)
                        // In a real app, you'd also update your backend/database
                    }
                )
            }
        }

        // Totals and Checkout Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White) // White background for this section
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Item Count
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "$itemCount Items", color = Color.Gray, fontSize = 14.sp)
                Text(text = "$${"%.2f".format(subtotal)}", color = Color.Gray, fontSize = 14.sp)
            }
            // Tax
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Tax", color = Color.Gray, fontSize = 14.sp)
                Text(text = "$${"%.2f".format(tax)}", color = Color.Gray, fontSize = 14.sp)
            }
            // Divider
            Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray))
            // Totals
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Totals", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Text(text = "$${"%.2f".format(total)}", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Checkout Button
            GlobalButton(
                text = "Checkout",
                onClick = { /* TODO: Implement checkout logic */ },
                modifier = Modifier.fillMaxWidth().height(56.dp) // Ensure button size
            )
        }
    }
}

// --- Preview ---
@Preview(showBackground = true, widthDp = 360, heightDp = 700)
@Composable
fun CartScreenPreview() {
    val imagenes: List<String> = listOf("Hola","Chau")
    MaterialTheme {
        val dummyProducts = listOf(
            Product(
                id = 5,
                title = "Royal Canin Adult",
                description = "for 2-3 years",
                price = 12.99,
                images = imagenes,
                brand = "RoyalCanin",
                thumbnail = ""
            ),
        )
        CartScreen(
            onBackClick = { println("Back button clicked") },
            initialCartItems = dummyProducts
        )
    }
}