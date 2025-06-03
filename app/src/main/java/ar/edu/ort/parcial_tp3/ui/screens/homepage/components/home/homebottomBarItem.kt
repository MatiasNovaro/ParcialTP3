package ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.painter.Painter
import ar.edu.ort.parcial_tp3.navigation.HomeBottomBarScreens
import ar.edu.ort.parcial_tp3.R

data class BottomBarItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String
)

// Lista de items del bottom bar

val bottomBarItems = listOf(
    BottomBarItem(
        title = "Home",
        icon = R.drawable.home,
        route = HomeBottomBarScreens.Home.screen
    ),
    BottomBarItem(
        title = "History",
        icon = R.drawable.time_circle,
        route = HomeBottomBarScreens.SearchScreen.screen
    ),
    BottomBarItem(
        title = "Cart",
        icon = R.drawable.bag,
        route = HomeBottomBarScreens.CartScreen.screen
    ),
    BottomBarItem(
        title = "Profile",
        icon = R.drawable.profile,
        route = HomeBottomBarScreens.ProfileScreen.screen
    )
)