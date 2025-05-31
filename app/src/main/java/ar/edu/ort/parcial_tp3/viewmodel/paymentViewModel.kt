package ar.edu.ort.parcial_tp3.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class PaymentUiState(
    val cardNumber: String = "",
    val cardName: String = "",
    val expirationDate: String = "",
    val cvv: String = "",
    val isPaypal: Boolean = false,
)

data class PaymentChooseUnit(
    val isChecked:Boolean,
    var text:String
)

class PaymentViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        PaymentUiState(
            cardNumber = "",
            cardName = "",
            expirationDate = "",
            cvv = "",
        )
    )
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()


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

    fun updateIsPaypal(isPaypal: Boolean) {
        _uiState.value = _uiState.value.copy(isPaypal = isPaypal)
    }
}

