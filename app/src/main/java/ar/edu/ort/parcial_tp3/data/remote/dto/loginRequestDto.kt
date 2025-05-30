package ar.edu.ort.parcial_tp3.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginRequestDto(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("expiresInMins")
    val expiresInMins: Int = 60 // valor por defecto
)