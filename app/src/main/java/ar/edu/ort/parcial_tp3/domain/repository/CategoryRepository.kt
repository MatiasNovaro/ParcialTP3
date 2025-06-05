package ar.edu.ort.parcial_tp3.domain.repository

import ar.edu.ort.parcial_tp3.domain.model.Category
import ar.edu.ort.parcial_tp3.util.Resource

interface CategoryRepository {
    suspend fun getCategories(): Resource<List<Category>>
}