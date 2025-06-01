package ar.edu.ort.parcial_tp3.data.mappers

import ar.edu.ort.parcial_tp3.data.remote.dto.ProductDto
import ar.edu.ort.parcial_tp3.domain.model.Product

fun ProductDto.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        price = price,
        brand = brand ?: "Sin marca",
        thumbnail = thumbnail ?: "",
        images = images ?: emptyList()
    )
}
