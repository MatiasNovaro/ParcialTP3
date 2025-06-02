package ar.edu.ort.parcial_tp3.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.ui.theme.Poppins
import ar.edu.ort.parcial_tp3.ui.theme.violetita

@Composable
fun ChangeEmailScreen(navController: NavController? = null) {
    var newEmail by remember { mutableStateOf("Abdul") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 40.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController?.popBackStack() },
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "Change Email",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Poppins,
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "New Email",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Poppins,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = newEmail,
                onValueChange = { newEmail = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6C5CE7),
                    unfocusedBorderColor = Color(0xFFE5E5E5),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(420.dp))

            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(bottom = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = violetita),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = "Save",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChangeEmailScreenPreview() {
    ChangeEmailScreen(
        navController = rememberNavController()
    )
}