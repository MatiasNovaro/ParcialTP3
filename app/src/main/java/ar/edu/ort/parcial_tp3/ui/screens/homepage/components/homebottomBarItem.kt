package ar.edu.ort.parcial_tp3.ui.screens.homepage.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import ar.edu.ort.parcial_tp3.navigation.HomeBottomBarScreens

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

// Lista de items del bottom bar
val bottomBarItems = listOf(
    BottomBarItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = HomeBottomBarScreens.Home.screen
    ),
    BottomBarItem(
        title = "History",
        icon = Icons.Default.Search,
        route = HomeBottomBarScreens.BestSellerScreen.screen
    ),
    BottomBarItem(
        title = "Lock",
        icon = Icons.Default.Lock,
        route = HomeBottomBarScreens.ProductDetailScreen.screen
    ),
    BottomBarItem(
        title = "Profile",
        icon = Icons.Default.Person,
        route = HomeBottomBarScreens.ProfileScreen.screen
    )
)