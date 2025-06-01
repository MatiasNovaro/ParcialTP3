package ar.edu.ort.parcial_tp3.data.mappers

import ar.edu.ort.parcial_tp3.data.remote.dto.CartDto
import ar.edu.ort.parcial_tp3.data.remote.dto.CartProductDto
import ar.edu.ort.parcial_tp3.domain.model.Cart
import ar.edu.ort.parcial_tp3.domain.model.CartProduct

fun CartProductDto.toCartProduct(): CartProduct {
    return CartProduct(
        id = id,
        title = title,
        price = price,
        quantity = quantity,
        total = total,
        discountPercentage = discountPercentage,
        discountedTotal = discountedTotal,
        thumbnail = thumbnail
    )
}

fun CartDto.toCart(): Cart {
    return Cart(
        id = id,
        products = products.map { it.toCartProduct() },
        total = total,
        discountedTotal = discountedTotal,
        userId = userId,
        totalProducts = totalProducts,
        totalQuantity = totalQuantity
    )
}