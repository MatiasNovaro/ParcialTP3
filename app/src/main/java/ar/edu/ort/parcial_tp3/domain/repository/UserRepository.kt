package ar.edu.ort.parcial_tp3.domain.repository

import ar.edu.ort.parcial_tp3.domain.model.User
import ar.edu.ort.parcial_tp3.util.Resource

interface UserRepository {
    suspend fun loginUser(username: String, password: String): Resource<User>
}