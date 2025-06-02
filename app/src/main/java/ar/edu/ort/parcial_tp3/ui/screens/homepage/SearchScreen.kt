package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.navigation.HomeBottomBarScreens
import ar.edu.ort.parcial_tp3.ui.components.globalToggle
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.Headerbox
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.HomeTopBarBis
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.NotificationItem
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.homeHorizontalFilter
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.homeRecentSearch
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.homeSearchTextField

@Composable
fun SearchScreen(navController: NavController)
{
    var selectedTab by remember { mutableStateOf("Activity") }

    Scaffold(
        topBar = {
            HomeTopBarBis(
                title = "Product Detail",
                onBackClick = { /* Navegar atrás */ },
                showFavButton = true,
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