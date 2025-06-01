package ar.edu.ort.parcial_tp3.ui.screens.homepage.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.domain.repository.ProductRepository
import ar.edu.ort.parcial_tp3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BestSellerViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productsState = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
    val productsState: StateFlow<Resource<List<Product>>> = _productsState.asStateFlow()

    fun getAllProducts() {
        viewModelScope.launch {
            _productsState.value = Resource.Loading()
            val result = productRepository.getAllProducts()
            _productsState.value = result
        }
    }
}
