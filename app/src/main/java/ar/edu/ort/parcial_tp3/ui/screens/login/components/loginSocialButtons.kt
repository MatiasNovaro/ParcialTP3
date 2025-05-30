package ar.edu.ort.parcial_tp3.ui.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.ort.parcial_tp3.R

@Composable
fun LoginSocialButtons(
    onGoogleClick: () -> Unit = {},
    onFacebookClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Botón de Google
        OutlinedButton(
            onClick = onGoogleClick,
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFE5E7EB),
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Icono de Google personalizable
                Image(
                    painter = painterResource(id = R.drawable.icon_google),
                    contentDescription = "Logo de Google",
                    modifier = Modifier.size(28.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Google",
                    modifier = Modifier.size(60.dp)
                )
            }
        }

        // Botón de Facebook
        OutlinedButton(
            onClick = onFacebookClick,
            modifier = Modifier
                .weight(1f)
                .height(56.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFE5E7EB),
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            border = null
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Icono de Facebook (puedes reemplazar con tu icono)
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Logo de Facebook",
                    modifier = Modifier.size(90.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginSocialButtonsPreview() {
    MaterialTheme {
        LoginSocialButtons(
            onGoogleClick = {
                // Acción para Google
            },
            onFacebookClick = {
                // Acción para Facebook
            }
        )
    }
}

