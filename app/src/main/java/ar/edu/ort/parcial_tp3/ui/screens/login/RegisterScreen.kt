package ar.edu.ort.parcial_tp3.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.ui.components.GlobalInput

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit = {},
    onLoginClick: () -> Unit = {},
    navController: NavController,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var isTermsAccepted by remember { mutableStateOf(false) }

    val isFormValid = email.isNotBlank() && password.isNotBlank() && fullName.isNotBlank() && isTermsAccepted

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Create New",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Account",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Water is life. Water is a basic human need. In various lines of life, humans need water.",
            fontSize = 14.sp,
            color = Color.Gray,
            textAlign = TextAlign.Start,
            lineHeight = 20.sp,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        GlobalInput(
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = "Full Name",
            keyboardType = KeyboardType.Text,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        GlobalInput(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email",
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        GlobalInput(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password",
            isPassword = true,
            keyboardType = KeyboardType.Password,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isTermsAccepted = !isTermsAccepted },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isTermsAccepted,
                onCheckedChange = { isTermsAccepted = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF6B46C1),
                    uncheckedColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            val termsText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("I Agree to the ")
                }
                withStyle(style = SpanStyle(color = Color(0xFF6B46C1), fontWeight = FontWeight.Medium)) {
                    append("Terms of Service")
                }
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append(" and ")
                }
                withStyle(style = SpanStyle(color = Color(0xFF6B46C1), fontWeight = FontWeight.Medium)) {
                    append("Privacy Policy")
                }
            }

            Text(
                text = termsText,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Spacer(modifier = Modifier.weight(1f))


        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append("Have an account? ")
            }
            withStyle(style = SpanStyle(color = Color(0xFF6B46C1), fontWeight = FontWeight.Bold)) {
                append("Login")
            }
        }

        Text(
            text = annotatedText,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onLoginClick() }
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        GlobalButton(
            text = "Get Started",
            onClick = onRegisterClick,
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen(
            onRegisterClick = {
                // Handle login
            },
            onLoginClick = {
                // Handle create account
            },
            navController = rememberNavController()
        )
    }
}