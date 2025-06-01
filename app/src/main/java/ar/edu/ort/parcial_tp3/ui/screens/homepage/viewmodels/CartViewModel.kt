package ar.edu.ort.parcial_tp3.ui.screens.homepage.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.ort.parcial_tp3.data.local.session.UserSession
import ar.edu.ort.parcial_tp3.domain.model.Cart
import ar.edu.ort.parcial_tp3.domain.repository.CartRepository
import ar.edu.ort.parcial_tp3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val userSession: UserSession
) : ViewModel() {

    private val _cartState = MutableStateFlow<Resource<List<Cart>>>(Resource.Loading())
    val cartState: StateFlow<Resource<List<Cart>>> = _cartState.asStateFlow()
    init {
        viewModelScope.launch {
            userSession.userIdFlow.collect(){ userId ->
                userId?.let {
                    getCartsByUser(it)
                }
            }
        }
    }
    private suspend fun getCartsByUser(userId: Int) {
        _cartState.value = cartRepository.getCartsByUser(userId)
        }
    }

