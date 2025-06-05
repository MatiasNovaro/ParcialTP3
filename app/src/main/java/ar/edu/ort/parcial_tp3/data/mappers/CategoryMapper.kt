package ar.edu.ort.parcial_tp3.data.mappers

import ar.edu.ort.parcial_tp3.data.remote.dto.CategoryDto
import ar.edu.ort.parcial_tp3.domain.model.Category

fun CategoryDto.toCategory(): Category {
    return Category(
        slug = slug,
        name = name,
        url = url
    )
}
