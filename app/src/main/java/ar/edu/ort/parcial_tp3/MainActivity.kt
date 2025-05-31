package ar.edu.ort.parcial_tp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.navigation.Screens
import ar.edu.ort.parcial_tp3.ui.screens.Home
import ar.edu.ort.parcial_tp3.ui.screens.login.ForgotPasswordResetScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.ForgotPasswordScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.LoginScreen
import ar.edu.ort.parcial_tp3.ui.screens.login.RegisterScreen
import ar.edu.ort.parcial_tp3.ui.screens.onboarding.SplashScreen
import ar.edu.ort.parcial_tp3.ui.theme.Parcial_TP3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial_TP3Theme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier= Modifier.padding(innerPadding),
                        navController=navController,
                        startDestination= Screens.SplashScreen.screen
                    ){
                        composable(Screens.Home.screen){ Home(navController)}
                        composable(Screens.SplashScreen.screen){ SplashScreen(onGetStartedClick = {},navController)}
                        composable(Screens.LoginScreen.screen){ LoginScreen(onLoginClick = {},navController = navController)}
                        composable(Screens.RegisterScreen.screen){ RegisterScreen(onRegisterClick = {},navController = navController)}
                        composable(Screens.ForgotPasswordScreen.screen){ ForgotPasswordScreen(onNextClick = {},navController = navController)}
                        composable(Screens.ForgotPasswordResetScreen.screen){ ForgotPasswordResetScreen(onResetClick = {},navController = navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Parcial_TP3Theme {
        Greeting("Android")
    }
}