package ar.edu.ort.parcial_tp3.ui.screens.homepage.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.ort.parcial_tp3.R // Make sure this points to your actual R file
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.DismissValue
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.rememberDismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold


// Dummy data class for preview
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val imageUrl: Int
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class) // Required for SwipeToDismissBox
@Composable
fun SwipeableProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onDelete: (Product) -> Unit // Callback when delete is confirmed
) {
    // State for managing the dismiss direction
    val dismissState = rememberDismissState(
        confirmStateChange = { dismissValue ->
            if (dismissValue == DismissValue.DismissedToStart) { // Swiped from right to left (to dismiss)
                onDelete(product)
                true // Confirm dismiss
            } else if (dismissValue == DismissValue.DismissedToEnd) { // Swiped from left to right (to reveal trash)
                // We don't want to dismiss if swiped left to right, just reveal
                false // Do not confirm dismiss
            } else {
                false // Do not confirm dismiss for other states
            }
        }
    )

    // AnimatedVisibility for the background (trash can)
    AnimatedVisibility(
        visible = dismissState.currentValue != DismissValue.Default, // Show background if not in default state
        enter = slideInHorizontally(animationSpec = tween(durationMillis = 300)) { -it }, // Slide in from right
        exit = slideOutHorizontally(animationSpec = tween(durationMillis = 300)) { -it } // Slide out to right
    ) {
        // Background content (the trash can)
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp) // Match card height
                .background(Color.Gray, RoundedCornerShape(12.dp))
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.CenterEnd // Align trash can to the right
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }

    // The actual card content that gets swiped
    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds = { FractionalThreshold(0.25f) },
        background = {
            // Trash icon revealed while swiping
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(horizontal = 20.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        dismissContent = {
            // Your card goes here
            Card(
                modifier = modifier
                    .fillMaxWidth()
                    .height(100.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        painter = painterResource(product.imageUrl),
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
                        )
                        Text(
                            text = product.description,
                            color = Color.Gray,
                            fontSize = 12.sp,
                            maxLines = 1,
                        )
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
    )

}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun SwipeableProductCardPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SwipeableProductCard(
                product = Product(
                    id = "1",
                    name = "Royal Canin Adult",
                    description = "for 2-3 years",
                    price = "$12.99",
                    imageUrl = R.drawable.royal_canin_adult // Replace with your actual drawable
                ),
                onDelete = { product ->
                    println("Delete product: ${product.name}")
                    // In a real app, you'd update your list state here
                }
            )
            SwipeableProductCard(
                product = Product(
                    id = "2",
                    name = "Another Pet Food",
                    description = "for all ages",
                    price = "$25.50",
                    imageUrl = R.drawable.royal_canin_adult // Placeholder
                ),
                onDelete = { product ->
                    println("Delete product: ${product.name}")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Example() {
    val dismissState = rememberDismissState(
      confirmStateChange = { dismissValue ->
            when (dismissValue) {
                DismissValue.DismissedToStart -> {
                    println("Swiped right to left")
                    true
                }
                DismissValue.DismissedToEnd -> {
                    println("Swiped left to right")
                    false
                }
                else -> false
            }
        }
    )
}
