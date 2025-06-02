package ar.edu.ort.parcial_tp3.ui.screens.homepage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun homeHorizontalFilter(){
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {

        CategoryItem(
            title = "",
            onClick = { }
        )

        CategoryItem(
            title = "Food",
            isSelected = true,
            onClick = { }
        )
        CategoryItem(
            title = "Toys",
            onClick = { }
        )
        CategoryItem(
            title = "Accesories",
            onClick = { }
        )
    }
}