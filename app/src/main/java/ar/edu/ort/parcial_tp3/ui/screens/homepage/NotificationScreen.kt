package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.navigation.HomeBottomBarScreens
import ar.edu.ort.parcial_tp3.ui.components.globalToggle
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeTopBarBis
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.NotificationItem

@Composable
fun NotificationScreen(navController: NavController)
 {
    var selectedTab by remember { mutableStateOf("Activity") }

    Scaffold(
        topBar = {
            HomeTopBarBis(
                title = "Product Detail",
                onBackClick = { /* Navegar atrás */ },
                showFavButton = false,
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            globalToggle(
                modifier = Modifier,
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            LazyColumn {
                items(4) {
                    NotificationItem(
                        imageRes = painterResource(id = R.drawable.royal_persian), // Reemplazá por tu imagen
                        title = "SALE 50%",
                        subtitle = "Check the details!",
                        like = false,
                        onClick = { /* Acción al hacer click */ }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun previewNoti(){
    NotificationScreen(navController = rememberNavController())
}