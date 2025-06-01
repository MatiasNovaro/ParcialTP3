package ar.edu.ort.parcial_tp3.ui.screens.payment

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.foundation.clickable // Añade esta importación
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text // Añade esta importación
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ar.edu.ort.parcial_tp3.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ar.edu.ort.parcial_tp3.domain.model.PaymentChooseUnit
import ar.edu.ort.parcial_tp3.navigation.Screens
import ar.edu.ort.parcial_tp3.ui.components.GlobalButton
import ar.edu.ort.parcial_tp3.ui.theme.Poppins
import ar.edu.ort.parcial_tp3.ui.theme.violetita

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentChooseScreen(
    onBackClick: () -> Unit,
    navController: NavController
) {
    var selectedPaymentMethod = remember { mutableStateOf<String?>(null) }
    val chooseButtons = remember {
        mutableStateListOf(
            PaymentChooseUnit(
                isChecked = false,
                text = ""
            ),
            PaymentChooseUnit(
                isChecked = false,
                text = ""
            )
        )
    }


    chooseButtons[0].text = stringResource(id = R.string.payment_choose_first_option)
    chooseButtons[1].text = stringResource(id = R.string.payment_choose_second_option)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.payment_method_top_bar),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                }
            )
            Text(
                text = stringResource(id = R.string.payment_choose_top_bar),
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                textAlign = TextAlign.Start
            )

            chooseButtons.forEachIndexed { index, info ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .border(
                            width = 1.dp,
                            color = if (info.isChecked) violetita else Color(0xFFD1D5DB),
                            shape = RoundedCornerShape(16.dp)  // Aumentado de 8.dp a 12.dp
                        )
                        .clickable {
                            chooseButtons.replaceAll {
                                it.copy(isChecked = it.text == info.text)
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 6.dp),  // Reducido el padding vertical de 16.dp a 12.dp
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = info.text,
                            fontSize = 14.sp,
                            color = if (info.isChecked) violetita else Color(0xFF9CA3AF)
                        )
                        RadioButton(
                            selected = info.isChecked,
                            onClick = {
                                chooseButtons.replaceAll {
                                    it.copy(isChecked = it.text == info.text)
                                }
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = violetita,
                                unselectedColor = Color.Gray
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            GlobalButton(
                onClick = {navController.navigate(Screens.PaymentSuccessScreen.screen)},
                text = stringResource(id = R.string.payment_choose_checkout_btn),
                modifier = Modifier.fillMaxWidth(),
                enabled = chooseButtons.any { it.isChecked }
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PaymentChooseScreenPreview() {
//    PaymentChooseScreen(
//        onBackClick = {},
//        onNavigateSuccess = {}
//    )
//}