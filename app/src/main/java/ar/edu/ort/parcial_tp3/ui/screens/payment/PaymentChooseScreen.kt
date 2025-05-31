package ar.edu.ort.parcial_tp3.ui.screens.payment

import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ar.edu.ort.parcial_tp3.R
import androidx.compose.ui.res.stringResource


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentChooseScreen(
    onBackClick: () -> Unit,
    onNavigateSuccess: () -> Unit
) {
    var selectedPaymentMethod = remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.payment_choose_top_bar)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(

                        selected = selectedPaymentMethod.value == "paypal",
                        onClick = { selectedPaymentMethod.value = "paypal" }
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedPaymentMethod.value == "paypal",
                    onClick = { selectedPaymentMethod.value = "paypal" }
                )
                Text(
                    text = stringResource(id = R.string.payment_choose_first_option),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = selectedPaymentMethod.value == "bank",
                        onClick = { selectedPaymentMethod.value = "bank" }
                    )
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedPaymentMethod.value == "bank",
                    onClick = { selectedPaymentMethod.value = "bank" }
                )
                Text(
                    text = stringResource(id = R.string.payment_choose_second_option),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Button(
            onClick = {onNavigateSuccess() },
            enabled = selectedPaymentMethod.value != null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = stringResource(id = R.string.payment_choose_checkout_btn))
        }
    }
}