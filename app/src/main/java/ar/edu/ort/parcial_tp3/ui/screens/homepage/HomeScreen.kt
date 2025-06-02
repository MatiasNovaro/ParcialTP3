package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.PromoBanner
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeBottomBar
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.ort.parcial_tp3.navigation.HomeBottomBarScreens
import ar.edu.ort.parcial_tp3.navigation.Screens
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.CategoryItem
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.CategorySection
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeCard
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeTopBar
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.homeBottomSheet
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.homeBottomSheetContent
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
    var showLocationSheet by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        bestSellerViewModel.getAllProducts()
    }
    Scaffold(
        topBar = { HomeTopBar(
            onLocationClick = { showLocationSheet = true },
            onNavigate = { route ->
                navController.navigate(route) {
                    popUpTo(HomeBottomBarScreens.Home.screen) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
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
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                            CategoryItem(
                                title = "",
                                onClick = { }
                            )

                            CategoryItem(
                                title = "Food",
                                isSelected = true,
                                onClick = { }
                            )
                            CategoryItem(
                                title = "Toys",
                                onClick = { }
                            )
                            CategoryItem(
                                title = "Accesories",
                                onClick = { }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    CategorySection(
                        title = "Best Seller",
                        isHorizontal = true,
                        onViewAllClick = {
                           navController.navigate(Screens.BestSellerScreen.screen)
                        }
                    ) {}
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ){
                        HomeCard(
                            product = products[0],
                            onAddToCart = { },
                            modifier = Modifier.weight(1f),
                            navController = navController
                        )
                        HomeCard(
                            product = products[1],
                            onAddToCart = { },
                            modifier = Modifier.weight(1f),
                            navController = navController
                        )
                    }
                }
            }
            is Resource.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    androidx.compose.material.Text(
                        text = (state as Resource.Error).message ?: "Error"
                    )
                }
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

