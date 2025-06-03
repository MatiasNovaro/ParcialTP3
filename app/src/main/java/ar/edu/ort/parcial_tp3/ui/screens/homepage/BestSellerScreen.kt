package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all.HomeCard
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all.HomeTopBarBis
import ar.edu.ort.parcial_tp3.ui.screens.homepage.viewmodels.BestSellerViewModel
import ar.edu.ort.parcial_tp3.util.Resource


@Composable
fun BestSellerScreen(
    navController: NavController,
    onAddToCart: (Product) -> Unit,
    bestSellerViewModel: BestSellerViewModel = hiltViewModel()
) {
    val state by bestSellerViewModel.productsState.collectAsState()
    LaunchedEffect(Unit) {
        bestSellerViewModel.getAllProducts()
    }
    when (state) {
        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Resource.Success -> {
            val products = (state as Resource.Success).data ?: emptyList()
            Scaffold(
                topBar = {
                    HomeTopBarBis(
                        title = "Best Sellers",
                        onBackClick = { navController.popBackStack() },
                        showFavButton = false,
                        navController = navController
                    )
                }
            ) { paddingValues ->
                LazyColumn(
                    modifier = Modifier.padding(paddingValues),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(products.chunked(2)) { rowProducts ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            for (product in rowProducts) {
                                HomeCard(
                                    product = product,
                                    onAddToCart = { onAddToCart(product) },
                                    modifier = Modifier.weight(1f),
                                    navController = navController
                                )
                            }
                            if (rowProducts.size < 2) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            }

        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = (state as Resource.Error).message ?: "Error")
            }
        }
    }
}


@Preview
@Composable
fun ProductGridPreview(){
//    BestSellerScreen(
//        products = sampleProducts,
//        onAddToCart = {}
//    )
}