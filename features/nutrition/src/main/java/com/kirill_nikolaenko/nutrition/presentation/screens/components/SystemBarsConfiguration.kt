
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat


@Composable
fun SystemBarsConfiguration(
    statusBarDarkIcons: Boolean,
    navigationBarDarkIcons: Boolean
) {
    val view = LocalView.current
    val activity = LocalActivity.current

    SideEffect {
        val window = activity?.window ?: return@SideEffect
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val controller = WindowInsetsControllerCompat(window, view)
        controller.isAppearanceLightStatusBars = statusBarDarkIcons
        controller.isAppearanceLightNavigationBars = navigationBarDarkIcons

        window.navigationBarColor = Color(238, 238, 238, 255).toArgb()

    }
}

