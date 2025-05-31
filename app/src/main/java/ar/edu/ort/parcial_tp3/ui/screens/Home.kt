package ar.edu.ort.parcial_tp3.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun Home(navController: NavController) {
    Text(
        text = "Home Screen",
        modifier = androidx.compose.ui.Modifier.padding(16.dp)
    )
}