package ar.edu.ort.parcial_tp3.ui.screens.homepage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.* // Using Material 3 components
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.DismissValue
import androidx.compose.material.DismissDirection
import androidx.compose.material.rememberDismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures // <-- Import for detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset // <-- Import for offset modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf // <-- Import for mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput // <-- Import for pointerInput
import androidx.compose.ui.platform.LocalDensity // <-- Import for LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset // <-- Import for IntOffset
import ar.edu.ort.parcial_tp3.R
import coil.compose.AsyncImage // <-- Import for AsyncImage (Requires Coil library)
import kotlin.math.roundToInt // <-- Import for roundToInt

// Dummy data class for preview
data class Product(
    val id: Long,
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: String
)

@Composable
fun CustomSwipeableProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onDelete: (Product) -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val maxSwipeDistance = 80.dp.value * LocalDensity.current.density

    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(100.dp)
                .align(Alignment.CenterEnd)
                .background(
                    Color(0xFFFF4444),
                    RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
                )
                .clickable { onDelete(product) },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragEnd = {

                            offsetX = if (offsetX < -maxSwipeDistance / 2) {
                                -maxSwipeDistance // Show delete button
                            } else {
                                0f
                            }
                        }
                    ) { _, dragAmount ->
                        val newOffset = (offsetX + dragAmount).coerceIn(-maxSwipeDistance, 0f)
                        offsetX = newOffset
                    }
                },
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
//                AsyncImage(
//                    model = product.imageUrl,
//                    contentDescription = "Product image for ${product.name}",
//                    modifier = Modifier
//                        .size(68.dp)
//                        .clip(RoundedCornerShape(8.dp)),
//                    contentScale = ContentScale.Crop
//                )
                Image(
                    painter = painterResource(R.drawable.royal_canin_adult),
                    contentDescription = "Product image for ${product.name}",
                    modifier = Modifier
                        .size(68.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = product.name,
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = product.description,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = product.price,
                        color = Color(0xFF8A2BE2),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true, widthDp = 360)
@Composable
fun CustomSwipeableProductCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CustomSwipeableProductCard(
                product = Product(
                    id = 1,
                    name = "Royal Canin Adult",
                    description = "for 2-3 years",
                    price = "$12.99",
                    imageUrl = "Texr" // Replace with a valid URL or local drawable
                ),
                onDelete = { product ->
                    println("Delete product: ${product.name}")
                    // In a real app, you'd update your list state here
                }
            )
            CustomSwipeableProductCard(
                product = Product(
                    id = 2,
                    name = "Another Pet Food",
                    description = "for all ages",
                    price = "$25.50",
                    imageUrl = "Hola" // Replace with a valid URL or local drawable
                ),
                onDelete = { product ->
                    println("Delete product: ${product.name}")
                }
            )
        }
    }
}