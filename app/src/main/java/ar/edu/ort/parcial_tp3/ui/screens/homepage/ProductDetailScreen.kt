package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ar.edu.ort.parcial_tp3.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.QuantitySelectorWithBackground
import coil.compose.AsyncImage

@Composable
fun ProductDetailScreen(product: Product, navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(25.dp))

               Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(331.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
                   AsyncImage(
                       model = product.thumbnail,
                       contentDescription = "Product image for ${product.title}",
                       modifier = Modifier
                           .fillMaxSize()
                           .clip(RoundedCornerShape(12.dp)),
                       contentScale = ContentScale.Crop
                   )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = product.title,
                color = Color.Black,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.W600
            )

            Text(
                text = product.description,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                QuantitySelectorWithBackground(
                    initialQuantity = 1,
                    onQuantityChanged = { newQuantity ->
                        println("Quantity changed to: $newQuantity")
                    }
                )
                Text(
                    text = "$"+product.price.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(5.dp))


            GlobalButton(
                text = "Add to Cart",
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                enabled = true
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}



//@Preview
//@Composable
//fun ProductDetailPreview(){
//        ProductDetailScreen(DetailedProduct(
//            imageUrl = "Nose",
//            title = "Royal Canin Adult",
//            price = "$12,99",
//            description = "The Persian cat has the longest and densest coat of all cat breeds. This means that it typically needs to consume more skin-health focused nutrients than other cat breeds. That’s why ROYAL CANIN® Persian Adult contains an exclusive complex of nutrients to help the skin’s barrier defence role to maintain good skin and coat health.",
//        ))
//}
