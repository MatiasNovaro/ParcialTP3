package ar.edu.ort.parcial_tp3.data.remote.api

import ar.edu.ort.parcial_tp3.data.remote.dto.CartResponseDto
import ar.edu.ort.parcial_tp3.data.remote.dto.CategoryDto
import ar.edu.ort.parcial_tp3.data.remote.dto.LoginRequestDto
import ar.edu.ort.parcial_tp3.data.remote.dto.LoginResponseDto
import ar.edu.ort.parcial_tp3.data.remote.dto.ProductsResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequestDto: LoginRequestDto
    ): Response<LoginResponseDto>

    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int? = 0,
        @Query("skip") skip: Int? = 0,
    ): Response<ProductsResponseDto>


    @GET("carts/user/{userId}")
    suspend fun getCartsByUser(
        @Path("userId") userId: Int
    ): Response<CartResponseDto>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<CategoryDto>>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String,
        @Query("limit") limit: Int? = null,
        @Query("skip") skip: Int? = null
    ): Response<ProductsResponseDto>

}