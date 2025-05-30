package ar.edu.ort.parcial_tp3.data.repository

import ar.edu.ort.parcial_tp3.data.remote.api.ApiService
import ar.edu.ort.parcial_tp3.domain.model.User
import ar.edu.ort.parcial_tp3.domain.repository.UserRepository
import ar.edu.ort.parcial_tp3.util.Resource
import ar.edu.ort.parcial_tp3.data.mappers.toUser
import ar.edu.ort.parcial_tp3.data.remote.dto.LoginRequestDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class UserRepositoryImpl(
    private val apiService: ApiService
) : UserRepository {

    override suspend fun loginUser(username: String, password: String): Resource<User> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.loginUser(
                    LoginRequestDto(username, password)
                )
                if (response.isSuccessful) {
                    response.body()?.let { loginResponse ->
                        Resource.Success(loginResponse.toUser())
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