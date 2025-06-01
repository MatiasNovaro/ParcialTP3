package ar.edu.ort.parcial_tp3.domain.repository

import ar.edu.ort.parcial_tp3.domain.model.Cart
import ar.edu.ort.parcial_tp3.util.Resource

interface CartRepository {
    suspend fun getCartsByUser(userId: Int): Resource<List<Cart>>
}