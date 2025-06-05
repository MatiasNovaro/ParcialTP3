package ar.edu.ort.parcial_tp3.ui.screens.settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.ui.theme.Poppins
import ar.edu.ort.parcial_tp3.ui.theme.violetita

@Composable
fun SettingNotificationScreen(navController: NavController? = null) {
    var likedPost by remember { mutableStateOf(true) }
    var newMessage by remember { mutableStateOf(true) }
    var itemSold by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        NotificationHeader(navController)

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Social",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = Poppins,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            NotificationToggleRow("Liked Post", likedPost) { likedPost = it }
            NotificationToggleRow("New Message", newMessage) { newMessage = it }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Store",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = Poppins,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            NotificationToggleRow("Item Sold", itemSold) { itemSold = it }
        }
    }
}

@Composable
fun NotificationHeader(navController: NavController? = null) {
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
            text = "Notification",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Poppins,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun NotificationToggleRow(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontFamily = Poppins,
            color = Color.Black
        )

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = violetita,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun NotificationScreenPreview() {
//    NotificationScreen(
//        navController = rememberNavController()
//    )
//}