package ar.edu.ort.parcial_tp3.ui.screens.homepage.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.zIndex
import ar.edu.ort.parcial_tp3.R

@Composable
fun PromoBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .height(160.dp)
    ) {
        // Fondo violeta
        Image(
            painter = painterResource(id = R.drawable.promo_banner_back),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Imagen del producto
        Image(
            painter = painterResource(id = R.drawable.double_royal),
            contentDescription = "Royal Canin Packs",
            modifier = Modifier
                .height(200.dp) // más grande aún
                .align(Alignment.CenterStart)
                .zIndex(1f) // asegura que quede encima del fondo
        )

        // Texto
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 24.dp)
                .width(180.dp)
        ) {
            Text(
                text = "Royal Canin\nAdult Pomeraniann", // mantiene el typo "Pomeraniann"
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Get an interesting promo here, without conditions",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}


@Preview
@Composable
fun previewPromoCard(){
    PromoBanner()
}