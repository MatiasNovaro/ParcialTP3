package ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.navigation.Screens
import coil.compose.AsyncImage


@Composable
fun HomeCard(
    product: Product,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Card(
        modifier = modifier.width(200.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Product Image
            AsyncImage(
                model = product.images[0],
                contentDescription = "Product image for ${product.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        navController.currentBackStackEntry?.savedStateHandle?.set("product", product)
                        navController.navigate(Screens.ProductDetailScreen.screen)
                    },
                contentScale = ContentScale.Crop
            )

//            Image(
//                painter = painterResource(R.drawable.product_image),
//                contentDescription = "Product image",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(1f)
//                    .clip(RoundedCornerShape(8.dp)),
//            )

            Spacer(modifier = Modifier.height(12.dp))

            // Product Title
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            //Spacer(modifier = Modifier.height(5.dp))

            // Price and Add Button Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$"+product.price.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                FloatingActionButton(
                    onClick = onAddToCart,
                    modifier = Modifier.size(32.dp),
                    containerColor = Color(0xFF6366F1), // Purple-blue color
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add to cart",
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun HomeCardPreview(){
//    HomeCard(
//        imageUrl = "Hola",
//        title = "RC Kitten",
//        price = "$20,99",
//        onAddToCart = {},
//        modifier = Modifier
//    )
//}