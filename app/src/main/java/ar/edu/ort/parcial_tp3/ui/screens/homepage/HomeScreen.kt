package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.PromoBanner
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.homeBottomBar
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.homeTopBar


@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        topBar = { homeTopBar() },
        bottomBar = {
            homeBottomBar(navController)
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
            /* CategorySection()
             Spacer(modifier = Modifier.height(24.dp))
             BestSellerSection()
             Spacer(modifier = Modifier.height(16.dp))*/
        }
    }
}

@Preview
@Composable
fun previewHome(){
    val navController = rememberNavController()
    HomeScreen(navController)
}