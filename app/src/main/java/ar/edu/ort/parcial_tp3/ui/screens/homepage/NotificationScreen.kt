package ar.edu.ort.parcial_tp3.ui.screens.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.ui.components.globalToggle
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.all.HomeTopBarBis
import ar.edu.ort.parcial_tp3.ui.screens.homepage.components.noti.NotificationItem

@Composable
fun NotificationScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("Activity") }

    data class NotificationData(
        val title: String,
        val subtitle: String,
        val type: String, // "like", "order", "news"
        val imageResId: Int
    )
    val activityItems = List(4) {
        NotificationData(
            title = "SALE 50%",
            subtitle = "Check the details!",
            type = "news",
            imageResId = R.drawable.royal_persian
        )
    }
    val sellerItems = listOf(
        NotificationData("You Got New Order!", "Please arrange delivery", "order", R.drawable.royal_persian),
        NotificationData("Momon", "Liked your Product", "like", R.drawable.momon),
        NotificationData("Ola", "Liked your Product", "like", R.drawable.ola),
        NotificationData("Raul", "Liked your Product", "like", R.drawable.raul),
        NotificationData("You Got New Order!", "Please arrange delivery", "order", R.drawable.royal_persian),
        NotificationData("You Got New Order!", "Please arrange delivery", "order", R.drawable.royal_persian),
        NotificationData("You Got New Order!", "Please arrange delivery", "order", R.drawable.royal_persian),
        NotificationData("Vito", "Liked your Product", "like", R.drawable.vito)
    )

    Scaffold(
        topBar = {
            HomeTopBarBis(
                title = "Notification",
                onBackClick = { navController.popBackStack() },
                showFavButton = false,
                navController = navController
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            globalToggle(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(240.dp) // Ajustá este valor si lo querés más chico
                    .height(40.dp),
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )

            LazyColumn {
                val itemsToShow = if (selectedTab == "Activity") activityItems else sellerItems
                items(itemsToShow) { item ->
                    NotificationItem(
                        imageRes = painterResource(id = item.imageResId),
                        title = item.title,
                        subtitle = item.subtitle,
                        type = item.type,
                        onClick = { /* Acción */ }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun previewNoti(){
    NotificationScreen(navController = rememberNavController())
}