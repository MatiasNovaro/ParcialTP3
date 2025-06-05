package ar.edu.ort.parcial_tp3.data.repository

import android.util.Log
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

    override suspend fun getAllProducts(limit: Int?, skip: Int?): Resource<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts(
                    limit = limit,
                    skip = 0,
                )
                if (response.isSuccessful) {
                    response.body()?.let { productsResponseDto ->
                        Log.d("Repository", "Response: ${response.body()}")
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

    override suspend fun getProductsByCategory(category: String, limit: Int?, skip: Int?): Resource<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProductsByCategory(category, limit, skip)
                if (response.isSuccessful) {
                    response.body()?.let { dto ->
                        Resource.Success(dto.products.map { it.toProduct() })
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
