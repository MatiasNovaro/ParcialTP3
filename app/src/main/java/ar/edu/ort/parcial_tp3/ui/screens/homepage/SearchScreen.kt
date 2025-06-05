package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all.HomeTopBarBis
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.HomeHorizontalFilter
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.search.homeRecentSearch
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.search.homeSearchTextField
import ar.edu.ort.parcial_tp3.ui.screens.homepage.viewmodels.BestSellerViewModel
import ar.edu.ort.parcial_tp3.util.Resource

@Composable
fun SearchScreen(navController: NavController,  bestSellerViewModel: BestSellerViewModel = hiltViewModel()) {
    var selectedTab by remember { mutableStateOf("Activity") }
    val categoryState by bestSellerViewModel.categoryState.collectAsState()
    LaunchedEffect(Unit) {
        bestSellerViewModel.getCategories()
    }
    when (categoryState) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is Resource.Success -> {
            val categories = (categoryState as Resource.Success).data ?: emptyList()
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
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {

                    homeSearchTextField()
                    Spacer(modifier = Modifier.height(24.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item {
                            HomeHorizontalFilter(
                                categories = categories,
                                navController = navController
                            )
                        }
                    }

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

        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                androidx.compose.material.Text(
                    text = (categoryState as Resource.Error).message ?: "Error"
                )
            }
        }
    }
}
//@Preview
//@Composable
//fun preview(){
//    SearchScreen(navController = rememberNavController())
//}