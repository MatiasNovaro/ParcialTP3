package ar.edu.ort.parcial_tp3.domain.repository

import ar.edu.ort.parcial_tp3.domain.model.Product
import ar.edu.ort.parcial_tp3.util.Resource

interface ProductRepository {
    suspend fun getAllProducts(): Resource<List<Product>>
}