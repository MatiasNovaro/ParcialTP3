package ar.edu.ort.parcial_tp3.ui.screens.homepage.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.ort.parcial_tp3.domain.model.Category
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.domain.repository.CategoryRepository
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
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _productsState = MutableStateFlow<Resource<List<Product>>>(Resource.Loading())
    val productsState: StateFlow<Resource<List<Product>>> = _productsState.asStateFlow()
    private val _categoryState = MutableStateFlow<Resource<List<Category>>>(Resource.Loading())
    val categoryState : StateFlow<Resource<List<Category>>> = _categoryState.asStateFlow()

    fun getAllProducts(limit: Int) {
        viewModelScope.launch {
            _productsState.value = Resource.Loading()
            val result = productRepository.getAllProducts(limit=limit, skip = 0)
            _productsState.value = result
        }
    }
    fun getProductsByCategory(category: String, limit: Int? = 0, skip: Int? = 0) {
        viewModelScope.launch {
            _productsState.value = Resource.Loading()
            val result = productRepository.getProductsByCategory(category, limit, skip)
            _productsState.value = result
        }
    }
    fun getCategories(){
        viewModelScope.launch {
            _categoryState.value = Resource.Loading()
            val result = categoryRepository.getCategories()
            _categoryState.value = result
        }
    }
}
