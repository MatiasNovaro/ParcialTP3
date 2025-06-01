package ar.edu.ort.parcial_tp3.domain.model

data class Cart(
val id: Int,
val products: List<CartProduct>,
val total: Double,
val discountedTotal: Double,
val userId: Int,
val totalProducts: Int,
val totalQuantity: Int
)
