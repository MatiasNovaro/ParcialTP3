package ar.edu.ort.parcial_tp3.data.repository

import ar.edu.ort.parcial_tp3.data.remote.api.ApiService
import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.domain.repository.ProductRepository
import ar.edu.ort.parcial_tp3.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ar.edu.ort.parcial_tp3.data.mappers.toProduct

class ProductRepositoryImpl(
    private val apiService: ApiService
) : ProductRepository {

    override suspend fun getAllProducts(): Resource<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getAllProducts()
                if (response.isSuccessful) {
                    response.body()?.let { productsResponseDto ->
                        val domainList = productsResponseDto.products.map { it.toProduct() }
                        Resource.Success(domainList)
                    } ?: Resource.Error("Respuesta vacía")
                } else {
                    Resource.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Excepción: ${e.message}")
            }
        }
    }
}
