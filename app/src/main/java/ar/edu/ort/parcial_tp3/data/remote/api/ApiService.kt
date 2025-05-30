package ar.edu.ort.parcial_tp3.data.remote.api

import ar.edu.ort.parcial_tp3.data.remote.dto.LoginRequestDto
import ar.edu.ort.parcial_tp3.data.remote.dto.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequestDto: LoginRequestDto
    ): Response<LoginResponseDto>
}