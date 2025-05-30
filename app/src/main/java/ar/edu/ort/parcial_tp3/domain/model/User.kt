package ar.edu.ort.parcial_tp3.domain.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val fullName: String,
    val gender: String,
    val imageUrl: String,
    val accessToken: String
)
