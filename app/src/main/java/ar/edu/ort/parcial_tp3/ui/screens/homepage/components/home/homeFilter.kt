package ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

@Composable
fun homeHorizontalFilter() {
    var selectedCategory by remember { mutableStateOf("Food") } // Valor inicial opcional

    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        listOf("All", "Food", "Toys", "Accessories").forEach { category ->
            CategoryItem(
                title = category,
                isSelected = selectedCategory == category,
                onClick = {
                    selectedCategory = category
                }
            )
        }
    }
}