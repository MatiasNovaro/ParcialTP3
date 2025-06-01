package ar.edu.ort.parcial_tp3.data.repository

import ar.edu.ort.parcial_tp3.data.mappers.toCart
import ar.edu.ort.parcial_tp3.data.remote.api.ApiService
import ar.edu.ort.parcial_tp3.domain.model.Cart
import ar.edu.ort.parcial_tp3.domain.repository.CartRepository
import ar.edu.ort.parcial_tp3.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepositoryImpl(
    private val apiService: ApiService
) : CartRepository {

    override suspend fun getCartsByUser(userId: Int): Resource<List<Cart>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getCartsByUser(userId)
                if (response.isSuccessful) {
                    response.body()?.let { dto ->
                        val carts = dto.carts.map { it.toCart() }
                        Resource.Success(carts)
                    } ?: Resource.Error("Empty response")
                } else {
                    Resource.Error("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Resource.Error("Exception: ${e.message}")
            }
        }
    }
}
