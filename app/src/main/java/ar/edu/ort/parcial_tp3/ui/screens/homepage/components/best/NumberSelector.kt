package ar.edu.ort.parcial_tp3.ui.screens.homepage.components.best

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuantitySelectorWithBackground(
    modifier: Modifier = Modifier,
    initialQuantity: Int = 1,
    onQuantityChanged: (Int) -> Unit
) {
    var quantity by remember { mutableStateOf(initialQuantity) }
    val roundedShape = RoundedCornerShape(24.dp)
    val backgroundColor = Color.White
    val borderColor = Color.Transparent

    Row(
        modifier = modifier
            .width(IntrinsicSize.Max) // Ensures the row takes only necessary width
            .height(48.dp) // Fixed height for a button-like appearance
            .background(
                color = backgroundColor,
                shape = roundedShape
            )
            .border(1.dp, borderColor, roundedShape),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Minus Button
        Button(
            onClick = {
                if (quantity > 0) {
                    quantity--
                    onQuantityChanged(quantity)
                }
            },
            modifier = Modifier
                .width(48.dp)
                .fillMaxHeight(),
            shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp, topEnd = 0.dp, bottomEnd = 0.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Transparent background
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "-", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        // Separator (optional, for visual distinction)
        Spacer(modifier = Modifier.width(1.dp).fillMaxHeight().background(borderColor))

        // Quantity Display
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .wrapContentWidth(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "$quantity", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Medium)
        }

        // Separator (optional, for visual distinction)
        Spacer(modifier = Modifier)

        // Plus Button
        Button(
            onClick = {
                quantity++
                onQuantityChanged(quantity)
            },
            modifier = Modifier
                .width(48.dp)
                .fillMaxHeight(),
            shape = RoundedCornerShape(topStart = 0.dp, bottomStart = 0.dp, topEnd = 24.dp, bottomEnd = 24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Transparent background
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = "+", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun QuantitySelectorWithBackgroundPreview() {
    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            QuantitySelectorWithBackground(
                onQuantityChanged = { println("Quantity: $it") }
            )
        }
    }
}