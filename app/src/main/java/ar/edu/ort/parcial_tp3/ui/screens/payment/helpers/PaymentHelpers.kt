package ar.edu.ort.parcial_tp3.ui.screens.payment.helpers

import ar.edu.ort.parcial_tp3.ui.screens.payment.PaymentTextFieldState

fun formatCardNumber(text: String, oldText: String, oldSelection: Int): PaymentTextFieldState {
    // Si el texto está vacío
    if (text.isEmpty()) return PaymentTextFieldState("", 0)

    // Obtiene solo los dígitos
    val currentDigits = text.filter { it.isDigit() }

    // Si hay más de 16 dígitos, ignora la entrada
    if (currentDigits.length > 16) return PaymentTextFieldState(oldText, oldSelection)

    // Si el último carácter no es un dígito, ignora la entrada
    val lastChar = text.lastOrNull()
    if (lastChar?.isDigit() != true && text.length > oldText.length) {
        return PaymentTextFieldState(oldText, oldSelection)
    }

    // Formatea el número
    val formatted = currentDigits.chunked(4).joinToString(" ")

    // Calcula la nueva posición del cursor
    val newCursor = formatted.length

    return PaymentTextFieldState(formatted, newCursor)
}

fun formatExpirationDate(text: String, oldText: String, oldSelection: Int): PaymentTextFieldState {
    // Si el texto nuevo es más corto que el anterior, probablemente sea un borrado
    if (text.length < oldText.length) {
        val digitsOnly = oldText.filter { it.isDigit() }
        if (digitsOnly.length <= 1) return PaymentTextFieldState("", 0)

        // Elimina el último dígito
        val newDigits = digitsOnly.dropLast(1)
        val formatted = if (newDigits.length > 2) {
            "${newDigits.take(2)}/${newDigits.drop(2)}"
        } else {
            newDigits
        }
        return PaymentTextFieldState(formatted, formatted.length)
    }

    // Para entrada nueva, toma solo el último dígito ingresado si es un número
    val lastChar = text.lastOrNull()
    if (lastChar?.isDigit() != true) return PaymentTextFieldState(oldText, oldSelection)

    val currentDigits = oldText.filter { it.isDigit() } + lastChar
    if (currentDigits.length > 4) return PaymentTextFieldState(oldText, oldSelection)

    val formatted = if (currentDigits.length > 2) {
        "${currentDigits.take(2)}/${currentDigits.drop(2)}"
    } else {
        currentDigits
    }

    return PaymentTextFieldState(formatted, formatted.length)
}