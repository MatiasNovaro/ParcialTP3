package ar.edu.ort.parcial_tp3.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.ui.components.GlobalInput
import ar.edu.ort.parcial_tp3.ui.screens.login.components.LoginSocialButtons
import ar.edu.ort.parcial_tp3.util.Resource

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel(), // Inject ViewModel
    onCreateAccountClick: () -> Unit = {},
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
    onGetStartedClick: ()-> Unit = {}
) {
    val loginState by loginViewModel.loginState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    val isFormValid = email.isNotBlank() && password.isNotBlank()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Hello,",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Welcome Back!",
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

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.or_separator),
            contentDescription = "or separator",
            modifier = Modifier
                .fillMaxWidth()
                .size(30.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        LoginSocialButtons(
            onGoogleClick = onGoogleClick,
            onFacebookClick = onFacebookClick
        )

        Spacer(modifier = Modifier.weight(1f))

        val annotatedText = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append("Don't have an account? ")
            }
            withStyle(style = SpanStyle(color = Color(0xFF6B46C1), fontWeight = FontWeight.Bold)) {
                append("Create Account")
            }
        }

        Text(
            text = annotatedText,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable{
                    navController.navigate("RegisterScreen")
                },
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Forgot password?",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onCreateAccountClick() }
                .padding(vertical = 8.dp)
                .clickable{
                    navController.navigate("ForgotPasswordScreen")
                },
            color = Color(0xFF6B46C1), fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        GlobalButton(
            text = "Get Started",
            onClick = {
                loginViewModel.login(email, password)
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))
        LaunchedEffect(loginState) {
            when (loginState) {
                is Resource.Success -> {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(
                        context,
                        (loginState as Resource.Error).message ?: "Error",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenDarkPreview() {
    LoginScreen(
        navController = rememberNavController()
    )
}