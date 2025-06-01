import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import ar.edu.ort.parcial_tp3.ui.theme.Poppins
import ar.edu.ort.parcial_tp3.ui.components.GlobalInput
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.ui.theme.violetita
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeCard
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.ui.screens.homepage.viewmodels.BestSellerViewModel
import ar.edu.ort.parcial_tp3.util.Resource

@Composable
fun ProfileScreen(
    isSellerMode: Boolean = false,
    onModeChange: (Boolean) -> Unit,
    bestSellerViewModel: BestSellerViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by bestSellerViewModel.productsState.collectAsState()
    LaunchedEffect(Unit) {
        bestSellerViewModel.getAllProducts()
    }
    when (state) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material.CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val products = (state as Resource.Success).data ?: emptyList()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                // Header con botones de Profile/Seller Mode
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Botón Profile
                    Button(
                        onClick = { onModeChange(false) },
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

                    // Botón Seller Mode
                    Button(
                        onClick = { onModeChange(true) },
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

                // Imagen de fondo y avatar
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                ) {
                    // Imagen de fondo con gradiente o imagen
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)
                            .clip(RoundedCornerShape(20.dp))
                    ) {
                        if (isSellerMode) {
                            // Imagen de fondo para Seller Mode
                            Image(
                                painter = painterResource(R.drawable.fondo_profile),
                                contentDescription = "Profile Background",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            // Gradiente para Profile Mode
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

                    // Avatar para Profile mode (fuera del Box de fondo)
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
                                painter = painterResource(R.drawable.p),
                                contentDescription = "Profile Logo",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }

                    // Avatar del usuario para Seller mode
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
                                painter = painterResource(R.drawable.abduldul),
                                contentDescription = "Avatar",
                                modifier = Modifier.size(90.dp),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(if (isSellerMode) 40.dp else 20.dp))

                // Nombre del usuario/tienda
                Text(
                    text = if (isSellerMode) "Abduldul" else "Pittashop",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                if (!isSellerMode) {
                    // Estadísticas para Profile mode
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem("109", "Followers")
                        StatItem("992", "Following")
                        StatItem("80", "Sales")
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Botones de navegación
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
                    // Botón Saved para Seller mode
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
                        TextButton(onClick = { }) {
                            Text("Edit Profile", fontFamily = Poppins, color = Color.Gray)
                        }
                    }

                }

                Spacer(modifier = Modifier.height(24.dp))

                // Productos usando HomeCard
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
        }

        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material.Text(text = (state as Resource.Error).message ?: "Error")
            }
        }
    }


    // Previews

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
        isSellerMode = false,
        onModeChange = { },
        navController = rememberNavController()
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenSellerModePreview() {
    ProfileScreen(
        isSellerMode = true,
        onModeChange = { },
        navController = rememberNavController()
    )
}