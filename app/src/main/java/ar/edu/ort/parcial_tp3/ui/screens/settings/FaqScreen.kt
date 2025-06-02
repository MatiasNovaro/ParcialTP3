package ar.edu.ort.parcial_tp3.ui.screens.settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ar.edu.ort.parcial_tp3.R
import ar.edu.ort.parcial_tp3.ui.theme.Poppins
import androidx.compose.ui.res.stringResource
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore


@Composable
fun FaqScreen(navController: NavController? = null) {
    val faqText = stringResource(id = R.string.faq_text)

    var expandedSecurity by remember { mutableStateOf(false) }
    var expandedPrivacy by remember { mutableStateOf(false) }
    var expandedPayment by remember { mutableStateOf(false) }
    var expandedAccount by remember { mutableStateOf(false) }
    var expandedDelivery by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
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
                text = "FAQ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            FaqCard(title = "Security", expanded = expandedSecurity, onToggle = {
                expandedSecurity = !expandedSecurity
            }, text = faqText)

            FaqCard(title = "Security", expanded = expandedPrivacy, onToggle = {
                expandedPrivacy = !expandedPrivacy
            }, text = faqText)

            FaqCard(title = "Security", expanded = expandedPayment, onToggle = {
                expandedPayment = !expandedPayment
            }, text = faqText)

            FaqCard(title = "Security", expanded = expandedAccount, onToggle = {
                expandedAccount = !expandedAccount
            }, text = faqText)

            FaqCard(title = "Security", expanded = expandedDelivery, onToggle = {
                expandedDelivery = !expandedDelivery
            }, text = faqText)
        }
    }
}

@Composable
fun FaqCard(title: String, expanded: Boolean, onToggle: () -> Unit, text: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onToggle() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = text,
                    fontSize = 14.sp,
                    fontFamily = Poppins,
                    color = Color.DarkGray
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FaqScreenPreview() {
    FaqScreen(
        navController = rememberNavController()
    )
}


