package ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.domain.model.Category
import ar.edu.ort.parcial_tp3.navigation.Screens

@Composable
fun HomeHorizontalFilter(categories:List<Category>, navController: NavController) {
    var selectedCategory by remember { mutableStateOf("Food") } // Valor inicial opcional

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        categories.forEach { category ->
            CategoryItem(
                title = category.name,
                isSelected = selectedCategory == category.name,
                onClick = {
                    navController.navigate(Screens.BestSellerScreen.createRoute(category.slug))
                }
            )
        }
    }
}