package org.example.white.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import whitelabel.composeapp.generated.resources.NotoNaskhArabic_Regular
import whitelabel.composeapp.generated.resources.Res
import whitelabel.composeapp.generated.resources.bebas_nue_regular


@Composable
fun AppTypography(): Typography {
//    val NotoFont = FontFamily(
//        Font(Res.font.NotoNaskhArabic_Regular, FontWeight.Bold),
//    )


    return Typography(
        headlineLarge = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        ),
        displaySmall = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    )
}