package ar.edu.ort.parcial_tp3.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GlobalButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = if (enabled) onClick else { {} },
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) Color(0xFF7140FD) else Color(0xFFD1D5DB),
            disabledContainerColor = Color(0xFFD1D5DB)
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            color = if (enabled) Color.White else Color(0xFF9CA3AF),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "GlobalButton Preview",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        // Botón habilitado
        Text("Enabled Button:")
        GlobalButton(
            text = "Enabled Button",
            onClick = { /* Action */ },
            enabled = true
        )

        // Botón deshabilitado
        Text("Disabled Button:")
        GlobalButton(
            text = "Disabled Button",
            onClick = { /* This won't execute */ },
            enabled = false
        )

        // Ejemplo con diferentes textos
        Text("Different Text:")
        GlobalButton(
            text = "Get Started",
            onClick = { /* Action */ }
        )

        GlobalButton(
            text = "Sign In",
            onClick = { /* Action */ }
        )
    }
}
