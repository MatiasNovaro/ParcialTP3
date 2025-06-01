package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.PromoBanner
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeBottomBar
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.ort.parcial_tp3.navigation.HomeBottomBarScreens
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.CategoryItem
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.CategorySection
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.homeTopBar

@Composable
fun HomeScreen(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        topBar = { homeTopBar() },
        bottomBar = {
            HomeBottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        // Pop up to the start destination to avoid building up a large stack
                        popUpTo(HomeBottomBarScreens.Home.screen) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(16.dp)
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
                    // Navegar a pantalla de categorías
                }
            ) {}
        }
    }
}
@Preview
@Composable
fun previewHome(){
    val navController = rememberNavController()
    HomeScreen(navController)
}