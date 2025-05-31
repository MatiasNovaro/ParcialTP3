package ar.edu.ort.parcial_tp3.data.mappers

import ar.edu.ort.parcial_tp3.data.remote.dto.LoginResponseDto
import ar.edu.ort.parcial_tp3.domain.model.User


fun LoginResponseDto.toUser(): User {
    return User(
        id = this.id,
        username = this.username,
        email = this.email,
        fullName = "${this.firstName} ${this.lastName}",
        gender = this.gender,
        imageUrl = this.image,
        accessToken = this.accessToken
    )
}
