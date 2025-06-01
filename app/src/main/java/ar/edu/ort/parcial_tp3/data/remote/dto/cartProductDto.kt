package ar.edu.ort.parcial_tp3.data.remote.dto

data class CartProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double,
    val thumbnail: String
)

data class CartDto(
    val id: Int,
    val products: List<CartProductDto>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
)
data class CartResponseDto(
    val carts: List<CartDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)
