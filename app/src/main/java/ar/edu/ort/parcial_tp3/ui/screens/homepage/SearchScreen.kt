package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all.HomeTopBarBis
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.homeHorizontalFilter
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.search.homeRecentSearch
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.search.homeSearchTextField

@Composable
fun SearchScreen(navController: NavController)
{
    var selectedTab by remember { mutableStateOf("Activity") }

    Scaffold(
        topBar = {
            HomeTopBarBis(
                title = "Search",
                onBackClick = { /* Navegar atrás */ },
                showFavButton = false,
                navController = navController,
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .padding(paddingValues)) {

            homeSearchTextField()
            Spacer(modifier = Modifier.height(24.dp))
            homeHorizontalFilter()
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Recent",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            homeRecentSearch()
            homeRecentSearch()
            homeRecentSearch()
            homeRecentSearch()

        }
    }
}

@Preview
@Composable
fun preview(){
    SearchScreen(navController = rememberNavController())
}