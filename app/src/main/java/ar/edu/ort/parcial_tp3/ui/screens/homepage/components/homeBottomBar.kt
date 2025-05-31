package ar.edu.ort.parcial_tp3.ui.screens.homepage.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.ort.parcial_tp3.navigation.Screens

data class BottomNavItem(val route: String, val icon: ImageVector, val label: String, val onClick: Unit)
@Composable
fun HomeBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(Screens.LoginScreen.screen, Icons.Default.Person, "Profile", navController.navigate(Screens.LoginScreen.screen)),
        BottomNavItem(Screens.SplashScreen.screen, Icons.Default.Search, "Search", navController.navigate(Screens.SplashScreen.screen)),
        BottomNavItem( Screens.Home.screen,Icons.Default.Home, "Home", navController.navigate(Screens.Home.screen)),
        //BottomNavItem(Screens.ForgotPasswordResetScreen.screen, Icons.Default.Settings, "Settings", navController.navigate(Screens.ForgotPasswordScreen.screen))
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(Color(0xFFF9F9F9), shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
        .padding(horizontal = 32.dp)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { index ->
            val selected = index.route == currentRoute
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .clickable {index.onClick}
            ) {
                Icon(
                    imageVector = index.icon ,
                    contentDescription = null,
                    tint = if (selected) Color(0xFF7140FD) else Color.LightGray,
                    modifier = Modifier.size(28.dp)
                )
                if (selected) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(Color(0xFF7140FD), shape = CircleShape)
                    )
                } else {
                    Spacer(modifier = Modifier.height(12.dp)) // para mantener alineado el espaciado
                }
            }
        }
    }
}
