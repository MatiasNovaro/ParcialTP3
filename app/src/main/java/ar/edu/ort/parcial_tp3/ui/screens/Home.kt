package ar.edu.ort.parcial_tp3.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.navigation.Screens


@Composable
fun Home(navController: NavController) {
    Text(
        text = "Home Screen",
        modifier = androidx.compose.ui.Modifier.padding(16.dp)
    )
    Button(
        onClick = { navController.navigate(Screens.PaymentMethodScreen.screen) },
        modifier = androidx.compose.ui.Modifier.padding(top = 16.dp)
    ){
        Text(
            text = "Go to Payment Method Screen",
            modifier = androidx.compose.ui.Modifier.padding(8.dp)
        )
    }
}