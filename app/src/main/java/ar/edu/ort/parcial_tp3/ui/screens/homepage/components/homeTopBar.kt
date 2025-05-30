package ar.edu.ort.parcial_tp3.ui.screens.homepage.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun homeTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ubicación
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Location",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Icon(
                    painter = painterResource(id = android.R.drawable.arrow_down_float),
                    contentDescription = "Dropdown",
                    modifier = Modifier.size(10.dp),
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Belgrano, CABA",
                fontSize = 16.sp,
                color = Color.Black
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            homeTopButton(
                icon = Icons.Default.Search,
                onClick = { /* Acción del botón de notificación */ },
                contentDescription = "Notifications"
            )
            homeTopButton(
                icon = Icons.Default.Notifications,
                onClick = { /* Acción del botón de notificación */ },
                contentDescription = "Notifications"
            )
        }
    }
}