package ar.edu.ort.parcial_tp3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.ort.parcial_tp3.data.local.entities.PaymentCard
import ar.edu.ort.parcial_tp3.domain.model.PaymentUiState
import ar.edu.ort.parcial_tp3.domain.repository.PaymentCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentCardRepository: PaymentCardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        PaymentUiState(
            cardNumber = "",
            cardName = "",
            expirationDate = "",
            cvv = ""
        )
    )
    val uiState: StateFlow<PaymentUiState> = _uiState.asStateFlow()

    private val _savedCard = MutableStateFlow<PaymentCard?>(null)
    val savedCard: StateFlow<PaymentCard?> = _savedCard.asStateFlow()

    init {
        viewModelScope.launch {
            paymentCardRepository.getCard().collect { card ->
                card?.let {
                    _uiState.value = PaymentUiState(
                        cardNumber = it.cardNumber,
                        cardName = it.cardName,
                        expirationDate = it.expirationDate,
                        cvv = it.cvv
                    )
                }
            }
        }
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

    fun saveCard() {
        viewModelScope.launch {
            val paymentCard = PaymentCard(
                id = 1,
                cardNumber = uiState.value.cardNumber,
                cardName = uiState.value.cardName,
                expirationDate = uiState.value.expirationDate,
                cvv = uiState.value.cvv
            )
            paymentCardRepository.upsertCard(paymentCard)
        }
    }
}