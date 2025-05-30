package ar.edu.ort.parcial_tp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var hasBeenTouched by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    LaunchedEffect(isFocused) {
        if (isFocused && !hasBeenTouched) {
            hasBeenTouched = true
        }
    }

    LaunchedEffect(isFocused) {
        if (!isFocused && hasBeenTouched) {
            // Como ya fue tocado esto lo hago para mantener el estado. Mejorare el codigo si encuentro una forma mejor de hacerlo
        }
    }

    val isEmpty = value.isBlank()
    val showError = isEmpty && hasBeenTouched

    val borderColor = if (showError) Color(0xFFEF4444) else Color(0xFF7140FD)
    val backgroundColor = if (showError) Color(0xFFFEF2F2) else Color(0xFFF3F4F6)

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                Text(
                    text = placeholder,
                    color = if (showError) Color(0xFFEF4444) else Color(0xFF7140FD),
                    fontSize = 14.sp
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(16.dp)
                ),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedLabelColor = if (showError) Color(0xFFEF4444) else Color(0xFF8B5CF6),
                unfocusedLabelColor = if (showError) Color(0xFFEF4444) else Color(0xFF8B5CF6),
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            singleLine = true,
            interactionSource = interactionSource
        )

        if (showError) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFFEF4444),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "!",
                        color = Color(0xFFEF4444),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Required Fields",
                    color = Color(0xFFEF4444),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GlobalInputPreview() {
    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("test123") }
    var emptyText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "GlobalInput Preview",
            style = MaterialTheme.typography.headlineMedium
        )

        Text("Input vacío (no rojo hasta tocar):")
        GlobalInput(
            value = emptyText,
            onValueChange = { emptyText = it },
            placeholder = "Email vacío",
            keyboardType = KeyboardType.Email
        )

        Text("Input con contenido (borde púrpura):")
        GlobalInput(
            value = passwordText,
            onValueChange = { passwordText = it },
            placeholder = "Password con contenido",
            isPassword = true
        )

        Text("Input interactivo:")
        GlobalInput(
            value = emailText,
            onValueChange = { emailText = it },
            placeholder = "Escribe tu email",
            keyboardType = KeyboardType.Email
        )
    }
}
