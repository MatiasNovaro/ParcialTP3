package ar.edu.ort.parcial_tp3.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun globalToggle(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color(0xFFF1F1F1)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        listOf("Activity", "Seller Mode").forEach { tab ->
            val isSelected = selectedTab == tab
            Text(
                text = tab,
                color = if (isSelected) Color.White else Color.Gray,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 10.dp)
                    .clip(RoundedCornerShape(30.dp))
                    .background(if (isSelected) Color(0xFF7A5EF8) else Color.Transparent)
                    .clickable { onTabSelected(tab) }
                    .wrapContentWidth(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
        }
    }
}