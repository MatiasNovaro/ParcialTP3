package ar.edu.ort.parcial_tp3.ui.screens.onboarding

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.CustomSwipeableProductCard
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.Product

data class CarouselItem(
    val description: String,
    val imageRes: Int
)

@Composable
fun SplashScreen(
    onGetStartedClick: () -> Unit = {},
    navController: NavController
) {
    val carouselItems = listOf(
        CarouselItem(
            description = "Get interesting promos here, register your account immediately so you can meet your animal needs.",
            imageRes = R.drawable.splash_image
        ),
        CarouselItem(
            description = "Connect with certified veterinarians and pet care specialists in your area for the best treatment.",
            imageRes = R.drawable.splash_image
        ),
        CarouselItem(
            description = "Discover high-quality food, toys, and accessories specially selected for your pet's happiness.",
            imageRes = R.drawable.splash_image
        )
    )

    val pagerState = rememberPagerState(pageCount = { carouselItems.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Meet your\nanimal needs\nhere",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
            lineHeight =56.sp
        )

        Spacer(modifier = Modifier.height(40.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val item = carouselItems[page]

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = "Carousel image ${page + 1}",
                    modifier = Modifier
                        .size(280.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = item.description,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 32.dp)
        ) {
            repeat(carouselItems.size) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == pagerState.currentPage) Color(0xFF6B46C1)
                            else Color(0xFFE5E7EB)
                        )
                )
            }
        }

        GlobalButton(
            text = "Get Started",
            onClick = onGetStartedClick,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun previewSplash(){
    SplashScreen(onGetStartedClick = {}, navController = rememberNavController())
}