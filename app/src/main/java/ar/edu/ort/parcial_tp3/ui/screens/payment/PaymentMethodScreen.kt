package ar.edu.ort.parcial_tp3.ui.screens.payment

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import ar.edu.ort.parcial_tp3.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.ui.components.GlobalInput
import ar.edu.ort.parcial_tp3.ui.screens.payment.helpers.formatCardNumber
import ar.edu.ort.parcial_tp3.viewmodel.PaymentViewModel
import ar.edu.ort.parcial_tp3.ui.screens.payment.helpers.formatExpirationDate
import ar.edu.ort.parcial_tp3.ui.theme.Poppins
import kotlin.compareTo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentMethodScreen(
    onBackClick: () -> Unit,
    onNavigateToChoose: () -> Unit,
    paymentViewModel: PaymentViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val uiState = paymentViewModel.uiState.collectAsState().value
    val isFormValid = remember(
        uiState.cardNumber,
        uiState.cardName,
        uiState.expirationDate,
        uiState.cvv
    ) {
        uiState.cardNumber.isNotEmpty() &&
                uiState.cardName.isNotEmpty() &&
                uiState.expirationDate.isNotEmpty() &&
                uiState.cvv.isNotEmpty()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal=8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.payment_method_top_bar),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold
                    )
                },
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

            Text(text = stringResource(id = R.string.payment_method_add_new_payment),fontFamily = Poppins,
                fontWeight = FontWeight.Bold)

            GlobalInput(
                value = uiState.cardNumber,
                onValueChange = { input ->
                    val state = formatCardNumber(input, uiState.cardNumber, input.length)
                    if (input.count { it.isDigit() } <= 16) {
                        paymentViewModel.updateCardNumber(state.text)
                    }
                },
                placeholder = stringResource(id = R.string.payment_method_card_number_placeholder)
            )

            GlobalInput(
                value = uiState.cardName,
                onValueChange = { paymentViewModel.updateCardName(it) },
                placeholder = stringResource(id = R.string.payment_method_card_name_placeholder)
            )

            GlobalInput(
                value = uiState.expirationDate,
                keyboardType = KeyboardType.Number,
                onValueChange = { input ->
                    val state = formatExpirationDate(input, uiState.expirationDate, input.length)
                    if (input.count { it.isDigit() } <= 4) {
                        paymentViewModel.updateExpirationDate(state.text)
                    }
                },
                placeholder = stringResource(id = R.string.payment_method_card_expiration_placeholder)
            )
            GlobalInput(
                isPassword=true,
                value = uiState.cvv,
                keyboardType = KeyboardType.Number,
                onValueChange = { input ->
                    if (input.length <= 3 && input.all { it.isDigit() }) {
                        paymentViewModel.updateCvv(input)
                    }
                },
                placeholder = stringResource(id = R.string.payment_method_card_cvv_placeholder)
            )


            Spacer(modifier = Modifier.weight(1f))

            GlobalButton(
                onClick = { onNavigateToChoose() },
                text = stringResource(id = R.string.payment_method_save_btn),
                modifier = Modifier.fillMaxWidth(),
                enabled = isFormValid
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PaymentMethodScreenPreview() {
    PaymentMethodScreen(
        onBackClick = {},
        onNavigateToChoose = {}//
    )
}