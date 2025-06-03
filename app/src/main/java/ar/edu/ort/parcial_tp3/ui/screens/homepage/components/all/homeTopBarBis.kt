package ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.home.homeTopButton

@Composable
fun HomeTopBarBis(
    title: String,
    onBackClick: () -> Unit,
    showFavButton: Boolean = false,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        homeTopButton(
            icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            onClick = {navController.popBackStack()},
            contentDescription = "Notifications",
            navController = navController
        )

        // Title
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

        // Right Action (optional)
        if (showFavButton) {
            homeTopButton(
                icon = Icons.Default.FavoriteBorder,
                onClick = { },
                contentDescription = "Notifications",
                navController = navController

            )
        } else {
            // Spacer to align the title centered if action button is hidden
            Spacer(modifier = Modifier.width(40.dp))
        }
    }
}

