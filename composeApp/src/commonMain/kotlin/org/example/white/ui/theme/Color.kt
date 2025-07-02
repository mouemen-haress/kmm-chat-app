import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryColor =Color(0xFF121212)
val secondaryColor = Color(0xFF1E1E1E)
val thirdColor =Color(0xFF00C853)

val textColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF)
    else Color(0xFF000000)