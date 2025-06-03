package ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
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
        route = HomeBottomBarScreens.SearchScreen.screen
    ),
    BottomBarItem(
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = HomeBottomBarScreens.CartScreen.screen
    ),
    BottomBarItem(
        title = "Profile",
        icon = Icons.Default.Person,
        route = HomeBottomBarScreens.ProfileScreen.screen
    )
)