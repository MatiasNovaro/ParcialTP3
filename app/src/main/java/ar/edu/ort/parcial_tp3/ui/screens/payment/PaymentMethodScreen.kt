package ar.edu.ort.parcial_tp3.ui.screens.payment

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.ort.parcial_tp3.R
import androidx.compose.ui.res.stringResource
import ar.edu.ort.parcial_tp3.viewmodel.PaymentViewModel
import ar.edu.ort.parcial_tp3.ui.screens.payment.helpers.formatCardNumber
import ar.edu.ort.parcial_tp3.ui.screens.payment.helpers.formatExpirationDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen(
    onBackClick: () -> Unit,
    onNavigateToChoose: () -> Unit,
    paymentViewModel: PaymentViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState = paymentViewModel.uiState.collectAsState().value
    val shouldShowError = remember(
        uiState.cardNumber,
        uiState.cardName,
        uiState.expirationDate,
        uiState.cvv
    ) {
        uiState.isError && (
                uiState.cardNumber.isEmpty() ||
                        uiState.cardName.isEmpty() ||
                        uiState.expirationDate.isEmpty() ||
                        uiState.cvv.isEmpty()
                )
    }

    val fieldColors = @androidx.compose.runtime.Composable { isEmpty: Boolean -> TextFieldDefaults.colors(
        focusedIndicatorColor = when {
            uiState.isError && isEmpty -> Color.Red
            else -> Color.Gray
        },
        unfocusedIndicatorColor = when {
            uiState.isError && isEmpty -> Color.Red
            else -> Color.Gray
        },
        focusedLabelColor = when {
            uiState.isError && isEmpty -> Color.Red
            else -> Color.Gray
        },
        unfocusedLabelColor = when {
            uiState.isError && isEmpty -> Color.Red
            else -> Color.Gray
        }
    )}

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
                title = { Text(text = stringResource(id = R.string.payment_method_top_bar)) },
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
            Text(
                text = stringResource(id = R.string.payment_method_add_new_payment),
            )

            OutlinedTextField(
                value = uiState.cardNumber,
                onValueChange = { input ->
                    val state = formatCardNumber(input, uiState.cardNumber, input.length)
                    if (input.count { it.isDigit() } <= 16) {
                        paymentViewModel.updateCardNumber(state.text)
                    }
                },
                label = { Text(text = stringResource(id = R.string.payment_method_card_number_placeholder)) },
                isError = uiState.isError && uiState.cardNumber.isEmpty(),
                colors = fieldColors(uiState.cardNumber.isEmpty()),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.cardName,
                onValueChange = { paymentViewModel.updateCardName(it) },
                label = { Text(text = stringResource(id = R.string.payment_method_card_name_placeholder)) },
                isError = uiState.isError && uiState.cardName.isEmpty(),
                colors = fieldColors(uiState.cardName.isEmpty()),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.expirationDate,
                onValueChange = { input ->
                    val state = formatExpirationDate(input, uiState.expirationDate, input.length)
                    if (input.count { it.isDigit() } <= 4) {
                        paymentViewModel.updateExpirationDate(state.text)
                    }
                },
                label = { Text(text = stringResource(id = R.string.payment_method_card_expiration_placeholder)) },
                isError = uiState.isError && uiState.expirationDate.isEmpty(),
                colors = fieldColors(uiState.expirationDate.isEmpty()),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.cvv,
                onValueChange = { input ->
                    if (input.length <= 4 && input.all { it.isDigit() }) {
                        paymentViewModel.updateCvv(input)
                    }
                },
                label = { Text(text = stringResource(id = R.string.payment_method_card_cvv_placeholder)) },
                isError = uiState.isError && uiState.cvv.isEmpty(),
                colors = fieldColors(uiState.cvv.isEmpty()),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                modifier = Modifier.fillMaxWidth()
            )

            if (shouldShowError) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.info_square),
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.payment_method_required_fields),
                        color = Color.Red
                    )
                }
            }
        }

        Button(
            onClick = { onNavigateToChoose() },
            enabled = paymentViewModel.isSaveEnabled,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = stringResource(id = R.string.payment_method_save_btn))
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PaymentMethodScreenPreview() {
    PaymentMethodScreen(
        onBackClick = {},
        onNavigateToChoose = {}// Implementación vacía para evitar el error
    )
}