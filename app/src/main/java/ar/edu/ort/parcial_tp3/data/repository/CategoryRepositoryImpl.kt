package ar.edu.ort.parcial_tp3.data.repository

import android.util.Log
import ar.edu.ort.parcial_tp3.data.mappers.toCategory
import ar.edu.ort.parcial_tp3.data.mappers.toProduct
import ar.edu.ort.parcial_tp3.data.remote.api.ApiService
import ar.edu.ort.parcial_tp3.domain.model.Category
import ar.edu.ort.parcial_tp3.domain.repository.CategoryRepository
import ar.edu.ort.parcial_tp3.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(
    private val apiService: ApiService
): CategoryRepository {

    override suspend fun getCategories(): Resource<List<Category>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCategories()
                if (response.isSuccessful) {
                    response.body()?.let { categoryResponseDto ->
                        Log.d("Repository", "Response: ${response.body()}")
                        val domainList = categoryResponseDto.map { it.toCategory() }
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