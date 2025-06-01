package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.domain.model.Cart
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.CustomSwipeableProductCard
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeCard
import ar.edu.ort.parcial_tp3.ui.screens.homepage.viewmodels.CartViewModel
import ar.edu.ort.parcial_tp3.util.Resource


data class CartItem(
    var quantity: Int
)
@Composable
fun CartScreen(
    onBackClick: () -> Unit,
    cartViewModel: CartViewModel = hiltViewModel(),
    navController: NavController
) {
    val cartState by cartViewModel.cartState.collectAsState()

    when (cartState) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (cartState as Resource.Error).message ?: "Error loading cart")
            }
        }

        is Resource.Success -> {
            val carts = (cartState as Resource.Success<List<Cart>>).data ?: emptyList()

            if (carts.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Cart is empty")
                }
                return
            }
            val cart = carts.first()

            val products = cart.products
            val subtotal = products.sumOf { it.price * it.quantity }
            val itemCount = products.sumOf { it.quantity }
            val taxRate = 0.05
            val tax = subtotal * taxRate
            val total = subtotal + tax
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .background(Color(0xFFF0F0F0))
            ) {
                // List of Cart Items
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(products, key = { it.id }) { cartProduct ->
                        CustomSwipeableProductCard(
                            product = cartProduct,
                            onDelete = {
                            }
                        )
                    }
                }


                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "$itemCount Items", color = Color.Gray, fontSize = 14.sp)
                        Text(
                            text = "$${"%.2f".format(subtotal)}",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Tax", color = Color.Gray, fontSize = 14.sp)
                        Text(text = "$${"%.2f".format(tax)}", color = Color.Gray, fontSize = 14.sp)
                    }

                    Spacer(
                        modifier = Modifier.height(1.dp).fillMaxWidth().background(Color.LightGray)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Totals",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "$${"%.2f".format(total)}",
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))


                    GlobalButton(
                        text = "Checkout",
                        onClick = { /* TODO: Implement checkout logic */ },
                        modifier = Modifier.fillMaxWidth().height(56.dp)
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true, widthDp = 360, heightDp = 700)
//@Composable
//fun CartScreenPreview() {
//    val imagenes: List<String> = listOf("Hola","Chau")
//    MaterialTheme {
//        val dummyProducts = listOf(
//            Product(
//                id = 5,
//                title = "Royal Canin Adult",
//                description = "for 2-3 years",
//                price = 12.99,
//                images = imagenes,
//                brand = "RoyalCanin",
//                thumbnail = ""
//            ),
//        )
//        CartScreen(
//            onBackClick = { println("Back button clicked") },
//            initialCartItems = dummyProducts
//        )
//    }
//}