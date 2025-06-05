package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.PromoBanner
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.HomeBottomBar
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.ort.parcial_tp3.navigation.HomeBottomBarScreens
import ar.edu.ort.parcial_tp3.navigation.Screens
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.CategorySection
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all.HomeCard
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all.HomeTopBar
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.HomeHorizontalFilter
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.homeBottomSheet
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.homeBottomSheetContent
import ar.edu.ort.parcial_tp3.ui.screens.homepage.viewmodels.BestSellerViewModel
import ar.edu.ort.parcial_tp3.util.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController,
               bestSellerViewModel: BestSellerViewModel = hiltViewModel()
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val state by bestSellerViewModel.productsState.collectAsState()
    val categoryState by bestSellerViewModel.categoryState.collectAsState()
    var showLocationSheet by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        bestSellerViewModel.getAllProducts(limit = 2)
        bestSellerViewModel.getCategories()
    }
    Scaffold(
        topBar = { HomeTopBar(
            onLocationClick = { showLocationSheet = true },
            navController=navController
        ) },
        bottomBar = {
            HomeBottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(HomeBottomBarScreens.Home.screen) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->

        homeBottomSheet(
            showBottomSheet = showLocationSheet,
            onDismiss = { showLocationSheet = false }
        ) {
            homeBottomSheetContent()
        }

        when{
            state is Resource.Loading || categoryState is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    androidx.compose.material.CircularProgressIndicator()
                }
            }
            state is Resource.Success && categoryState is Resource.Success -> {
                val products = (state as Resource.Success).data ?: emptyList()
                val categories =(categoryState as Resource.Success).data?: emptyList()
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    PromoBanner()
                    Spacer(modifier = Modifier.height(24.dp))

                    CategorySection(
                        title = "Category",
                        isHorizontal = true,
                        onViewAllClick = {
                            // Navegar a pantalla de categorías
                        }
                    ) {
                        HomeHorizontalFilter(
                            categories=categories,
                            navController = navController
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    CategorySection(
                        title = "Best Seller",
                        isHorizontal = true,
                        onViewAllClick = {
                           navController.navigate(Screens.BestSellerScreen.createRoute())
                        }
                    ) {}
                    if (products.size >= 2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            products.take(2).forEach { product ->
                                HomeCard(
                                    product = product,
                                    onAddToCart = { },
                                    modifier = Modifier.weight(1f),
                                    navController = navController
                                )
                            }
                        }
                    } else {
                        Text("Not enough products to show")
                    }

                }
            }
            state is Resource.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    androidx.compose.material.Text(
                        text = (state as Resource.Error).message ?: "Error"
                    )
                }
            }
            categoryState is Resource.Error -> {
                Text(text = (categoryState as Resource.Error).message ?: "Error cargando categorías")
            }
        }
    }
}
@Preview
@Composable
fun previewHome(){
    val navController = rememberNavController()
    HomeScreen(navController)
}

