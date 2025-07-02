package org.example.white.ui.theme


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import primaryColor
import secondaryColor
import thirdColor
import whitelabel.composeapp.generated.resources.Res
import whitelabel.composeapp.generated.resources.bebas_nue_regular

private val darkColorScheme = AppColorScheme(
    primary = primaryColor,
    secondary = secondaryColor,
    third = thirdColor
)


@OptIn(ExperimentalResourceApi::class)
@Composable
fun GetBebasFontFamily() = FontFamily(Font(Res.font.bebas_nue_regular))


@Composable
fun type() = AppTypography(
    titleLarge = titleLarge(),
    titleNormal = headlineMedium(),
    body = headlineMedium(),
    labelLarge = headlineMedium(),
    labelNormal = labelNormal(),
    labelSmall = headlineSmall(),
    buttonText = headLineButton()
)

private val shape = AppShape(
    container = RoundedCornerShape(30.dp),
    button = RoundedCornerShape(50)
)

private val margin = AppMargin(
    small = 8.dp,
    large = 24.dp

)
private val size = AppSize(
    large = 20.dp,
    medium = 16.dp,
    normal = 12.dp,
    small = 8.dp,
    icon = 32.dp,
)


@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = darkColorScheme

    CompositionLocalProvider(
        LocalAppColorScheme provides colorScheme,
        LocalAppShape provides shape,
        LocalAppSize provides size,
        LocalAppMargin provides margin,
        LocalAppTypography provides type(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(colorScheme.primary)
        ) {
            content()
        }

    }
}

object AppTheme {
    val colorScheme: AppColorScheme
        @Composable get() = LocalAppColorScheme.current

    val shape: AppShape
        @Composable get() = LocalAppShape.current

    val size: AppSize
        @Composable get() = LocalAppSize.current

    val margin: AppMargin
        @Composable get() = LocalAppMargin.current

    val type: AppTypography
        @Composable get() = LocalAppTypography.current
}