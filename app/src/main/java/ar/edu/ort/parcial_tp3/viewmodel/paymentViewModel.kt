package ar.edu.ort.parcial_tp3.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PaymentUiState(
    val cardNumber: String = "",
    val cardName: String = "",
    val expirationDate: String = "",
    val cvv: String = "",
    val isError: Boolean = false
)

class PaymentViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        PaymentUiState(
            cardNumber = "",
            cardName = "",
            expirationDate = "",
            cvv = "",
            isError = true
        )
    )
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    // Solo actualizar isError cuando todos los campos estén llenos
    private fun updateErrorState() {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            isError = currentState.cardNumber.isEmpty() ||
                    currentState.cardName.isEmpty() ||
                    currentState.expirationDate.isEmpty() ||
                    currentState.cvv.isEmpty()
        )
    }
    val isSaveEnabled: Boolean
        get() = with(_uiState.value) {
            cardNumber.isNotEmpty() &&
                    cardName.isNotEmpty() &&
                    expirationDate.isNotEmpty() &&
                    cvv.isNotEmpty()
        }

    fun updateCardNumber(number: String) {
        _uiState.value = _uiState.value.copy(cardNumber = number)
    }

    fun updateCardName(name: String) {
        _uiState.value = _uiState.value.copy(cardName = name)
    }

    fun updateExpirationDate(date: String) {
        _uiState.value = _uiState.value.copy(expirationDate = date)
    }

    fun updateCvv(cvv: String) {
        _uiState.value = _uiState.value.copy(cvv = cvv)
    }
}

