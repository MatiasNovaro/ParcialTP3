import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.theme.Poppins
import ar.edu.ort.parcial_tp3.ui.theme.violetita
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all.HomeCard
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.HomeBottomBar
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.navigation.Screens
import ar.edu.ort.parcial_tp3.ui.screens.homepage.viewmodels.BestSellerViewModel
import ar.edu.ort.parcial_tp3.util.Resource

@Composable
fun ProfileScreen(
    bestSellerViewModel: BestSellerViewModel = hiltViewModel(),
    navController: NavController
) {
    var isSellerMode by remember { mutableStateOf(false) }

    val state by bestSellerViewModel.productsState.collectAsState()

    LaunchedEffect(Unit) {
        bestSellerViewModel.getAllProducts(limit = 6)
    }

    when (state) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material.CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val products = (state as Resource.Success).data ?: emptyList()
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp)
                        .padding(bottom = if (!isSellerMode) 80.dp else 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Button(
                            onClick = {
                                isSellerMode = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (!isSellerMode) violetita else Color.LightGray,
                                contentColor = if (!isSellerMode) Color.White else Color.Black
                            ),
                            shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp),
                            modifier = Modifier
                                .height(40.dp)
                                .width(100.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Profile",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }

                        Button(
                            onClick = {
                                isSellerMode = true
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isSellerMode) violetita else Color.LightGray,
                                contentColor = if (isSellerMode) Color.White else Color.Black
                            ),
                            shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
                            modifier = Modifier
                                .height(40.dp)
                                .width(120.dp),
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = "Seller Mode",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp)
                                .clip(RoundedCornerShape(20.dp))
                        ) {
                            if (!isSellerMode) {
                                Image(
                                    painter = painterResource(R.drawable.fondo_profile),
                                    contentDescription = "Profile Background",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            brush = Brush.linearGradient(
                                                colors = listOf(Color(0xFFFFB366), Color(0xFFFF8A50))
                                            )
                                        )
                                )
                            }
                        }
                        if (isSellerMode) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .background(Color.White, CircleShape)
                                    .align(Alignment.BottomCenter)
                                    .offset(y = (-1).dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.p),
                                    contentDescription = "Profile Logo",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }

                        if (!isSellerMode) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .background(Color.White, CircleShape)
                                    .align(Alignment.BottomCenter)
                                    .offset(y = (-1).dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.abduldul),
                                    contentDescription = "Avatar",
                                    modifier = Modifier.size(90.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(if (!isSellerMode) 40.dp else 20.dp))

                    Text(
                        text = if (!isSellerMode) "Abduldul" else "Pittashop",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Poppins,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    if (isSellerMode) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StatItem("109", "Followers")
                            StatItem("992", "Following")
                            StatItem("80", "Sales")
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = violetita),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text("Product", fontFamily = Poppins, color = Color.White)
                            }

                            TextButton(onClick = { }) {
                                Text("Sold", fontFamily = Poppins, color = Color.Gray)
                            }

                            TextButton(onClick = { }) {
                                Text("Stats", fontFamily = Poppins, color = Color.Gray)
                            }
                        }
                    } else {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(
                                onClick = { },
                                colors = ButtonDefaults.buttonColors(containerColor = violetita),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Text("Saved", fontFamily = Poppins, color = Color.White)
                            }
                            TextButton(onClick = {
                                navController.navigate(Screens.SettingsScreen.screen)
                            }) {
                                Text("Edit Profile", fontFamily = Poppins, color = Color.Gray)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

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
                                        product = product,
                                        onAddToCart = {  },
                                        modifier = Modifier.weight(1f),
                                        navController = navController
                                    )
                                }
                                if (rowProducts.size < 2) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                }
                if (!isSellerMode) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                    ) {
                        HomeBottomBar(
                            currentRoute = "ProfileScreen",
                            onNavigate = { route ->
                                when (route) {
                                    "Home" -> navController.navigate("Home")
                                    "BestSellerScreen" -> navController.navigate("BestSellerScreen")
                                    "CartScreen" -> navController.navigate("CartScreen")
                                    "ProfileScreen" -> {  }
                                    else -> navController.navigate(route)
                                }
                            }
                        )
                    }
                }
            }
        }

        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material.Text(text = (state as Resource.Error).message ?: "Error")
            }
        }
    }
}

@Composable
fun StatItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            fontFamily = Poppins
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        navController = rememberNavController()
    )
}