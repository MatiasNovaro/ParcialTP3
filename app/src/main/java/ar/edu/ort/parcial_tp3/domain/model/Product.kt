package ar.edu.ort.parcial_tp3.domain.model

data class Product(
val id: Int,
val title: String,
val description: String,
val price: Double,
val brand: String,
val thumbnail: String,
val images: List<String>
)

