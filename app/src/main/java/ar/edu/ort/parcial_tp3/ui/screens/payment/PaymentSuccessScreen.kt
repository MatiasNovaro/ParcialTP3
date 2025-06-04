package ar.edu.ort.parcial_tp3.ui.screens.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.navigation.Screens
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.ui.theme.Poppins

@Composable
fun PaymentSuccessScreen(
    navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(0.1f)) // Añade espacio en la parte superior

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.payment_success_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins,
                    fontSize = 42.sp,
                    lineHeight = 48.sp,
                    modifier = Modifier.fillMaxWidth(),  // Ocupa todo el ancho disponible
                    textAlign = TextAlign.Start  // Alinea el texto a la izquierda
                )

                Spacer(modifier = Modifier.height(24.dp))


                Text(
                    text = stringResource(id = R.string.payment_success_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = Poppins,
                    fontSize = 12.sp,

                    )
            }

            Spacer(modifier = Modifier.weight(1f))

            GlobalButton(
                onClick = {navController.navigate(Screens.Home.screen)},
                text = stringResource(id = R.string.payment_success_home_btn),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PaymentSuccessScreenPreview() {
    val navController = rememberNavController()
    PaymentSuccessScreen(navController = navController)
}