package ar.edu.ort.parcial_tp3.data.remote.api

import ar.edu.ort.parcial_tp3.data.remote.dto.CartResponseDto
import ar.edu.ort.parcial_tp3.data.remote.dto.LoginRequestDto
import ar.edu.ort.parcial_tp3.data.remote.dto.LoginResponseDto
import ar.edu.ort.parcial_tp3.data.remote.dto.ProductsResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequestDto: LoginRequestDto
    ): Response<LoginResponseDto>

    @GET("products")
    suspend fun getAllProducts(): Response<ProductsResponseDto>

    @GET("carts/user/{userId}")
    suspend fun getCartsByUser(
        @Path("userId") userId: Int
    ): Response<CartResponseDto>

}