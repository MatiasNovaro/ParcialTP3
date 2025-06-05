package ar.edu.ort.parcial_tp3.ui.screens.settings

import ProfileScreen
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.ui.theme.Poppins

@Composable
fun AccountScreen(navController: NavController) {
    var name by remember { mutableStateOf("Abdul") }
    var username by remember { mutableStateOf("Abdul") }
    var email by remember { mutableStateOf("Abdul") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AccountHeader(navController = navController)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.fondo_profile),
                contentDescription = "Profile Background",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.9f), CircleShape)
                    .align(Alignment.TopEnd)
                    .offset(x = (-0).dp, y = 0.dp)
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.edit),
                    contentDescription = "Edit Background",
                    modifier = Modifier.size(16.dp)
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = (-1).dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.abduldul),
                        contentDescription = "Avatar",
                        modifier = Modifier.size(90.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(Color.White, CircleShape)
                        .align(Alignment.TopEnd)
                        .offset(x = 1.dp, y = (-1).dp)
                        .clickable {
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.edit),
                        contentDescription = "Edit Avatar",
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Abduldul",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Name",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Poppins,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6C5CE7),
                    unfocusedBorderColor = Color(0xFFE5E5E5),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Username",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Poppins,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6C5CE7),
                    unfocusedBorderColor = Color(0xFFE5E5E5),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Email",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Poppins,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF6C5CE7),
                    unfocusedBorderColor = Color(0xFFE5E5E5),
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                ),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        Button(
            onClick = {
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6C5CE7)
            ),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(
                text = "Save Changes",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(25.dp))
    }
}

@Composable
fun AccountHeader(navController: NavController? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    navController?.popBackStack()
                },
            tint = Color.Black
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = "Account",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AccountScreenPreview() {
    AccountScreen(
        navController = rememberNavController()
    )
}