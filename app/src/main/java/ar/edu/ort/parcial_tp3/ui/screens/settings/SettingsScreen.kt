package ar.edu.ort.parcial_tp3.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.ui.theme.Poppins
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.navigation.Screens
import ar.edu.ort.parcial_tp3.ui.theme.violetita

@Composable
fun SettingsScreen(
    navController: NavController? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Header(navController = navController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp)
        ) {

            SectionTitle("Account")
            Spacer(modifier = Modifier.height(16.dp))

            SettingsItemDrawable(
                iconRes = R.drawable.profile,
                title = "Account",
                onClick = {
                    navController?.navigate(Screens.AccountScreen.screen)
                }
            )

            SettingsItemDrawable(
                iconRes = R.drawable.home,
                title = "Address",
                onClick = {   }
            )

            SettingsItemDrawable(
                iconRes = R.drawable.notification,
                title = "Notification",
                onClick = {
                    navController?.navigate(Screens.SettingsNotificationScreen.screen)
                }
            )

            SettingsItemDrawable(
                iconRes = R.drawable.wallet,
                title = "Payment Method",
                onClick = {
                    navController?.navigate(Screens.PaymentMethodScreen.screen)
                }
            )

            SettingsItemDrawable(
                iconRes = R.drawable.danger,
                title = "Privacy",
                onClick = {
                    navController?.navigate(Screens.PrivacyScreen.screen)
                }
            )

            SettingsItemDrawable(
                iconRes = R.drawable.password,
                title = "Security",
                onClick = {
                    navController?.navigate(Screens.SecurityScreen.screen)
                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            SectionTitle("Help")
            Spacer(modifier = Modifier.height(16.dp))

            SettingsItemDrawable(
                iconRes = R.drawable.call,
                title = "Contact Us",
                onClick = {   }
            )

            SettingsItemDrawable(
                iconRes = R.drawable.document,
                title = "FAQ",
                onClick = {
                    navController?.navigate(Screens.FaqScreen.screen)
                }
            )

            Spacer(modifier = Modifier.height(25.dp))

            LogOutButton(
                onClick = {   }
            )
        }
    }
}

@Composable
fun Header(navController: NavController? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
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
            text = "Settings Page",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Poppins,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = Poppins,
        color = Color.Black
    )
}

@Composable
fun SettingsItemDrawable(
    iconRes: Int,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(20.dp),
                tint = Color(0xFF6B7280)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Poppins,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Arrow",
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF9CA3AF)
        )
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(20.dp),
                tint = Color(0xFF6B7280)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Poppins,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Arrow",
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF9CA3AF)
        )
    }
}

@Composable
fun LogOutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(28.dp),
        border = androidx.compose.foundation.BorderStroke(
            2.dp,
            violetita
        )
    ) {
        Text(
            text = "Log Out",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = violetita
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    MaterialTheme {
        SettingsScreen()
    }
}