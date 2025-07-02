package org.example.white.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import whitelabel.composeapp.generated.resources.Res
import whitelabel.composeapp.generated.resources.*

@Composable
fun headlineMedium(): TextStyle {
    val density = LocalDensity.current
    var lineHeight = 32.sp
    var textSize = 24.sp * density.fontScale
    return TextStyle(
        fontWeight = FontWeight(600),
        fontSize = textSize,
        lineHeight = lineHeight,
        fontFamily = FontFamily(Font(Res.font.NotoNaskhArabic_Regular)),
        color = Color.White
    )

}

@Composable
fun headlineSmall(): TextStyle {
    val density = LocalDensity.current
    var textSize = 10.sp * density.fontScale
    return TextStyle(
        fontWeight = FontWeight(400),
        fontSize = textSize,
        fontFamily = FontFamily(Font(Res.font.NotoNaskhArabic_Regular)),
    )

}

@Composable
fun headLineButton(): TextStyle {
    val density = LocalDensity.current
    var textSize = 16.sp * density.fontScale
    var lineHeight = 20.sp
    return TextStyle(
        fontWeight = FontWeight(400),
        fontSize = textSize,
        lineHeight = lineHeight,
        color = Color.White,
        fontFamily = FontFamily(Font(Res.font.NotoNaskhArabic_Regular))
    )
}

@Composable
fun labelNormal(): TextStyle {
    val density = LocalDensity.current
    val textSize = 16.sp * density.fontScale

    return TextStyle(
        fontWeight = FontWeight(400),
        fontSize = textSize,
        lineHeight = 36.sp,
        color = Color.White,
        fontFamily = FontFamily(Font(Res.font.NotoNaskhArabic_Regular))
    )

}

@Composable
fun titleLarge(): TextStyle {
    val density = LocalDensity.current
    val textSize = 30.sp * density.fontScale

    return TextStyle(
        fontWeight = FontWeight(400),
        fontSize = textSize,
        lineHeight = 50.sp,
        color = AppTheme.colorScheme.third,
        fontFamily = FontFamily(Font(Res.font.NotoNaskhArabic_Regular))
    )

}