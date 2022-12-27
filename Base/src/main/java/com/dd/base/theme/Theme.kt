package com.dd.base.theme


import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

const val THEME_COLOR_KEY = "theme_color"
const val THEME_STYLE_KEY = "theme_style"

//夜色主题
private val DarkColorPalette = AppColors(
    themeUi = black1,
    background = black2,
    listItem = black3,
    divider = black4,
    textPrimary = white4,
    textSecondary = grey1,
    mainColor = white,
    card = white1,
    icon = white4,
    white = white4,
    warn = warn,
    success = green3,
    error =red2,
    primaryBtnBg = black1,
    secondBtnBg = white1,
    hot = red,
    placeholder = grey1,
    transparent = Transparent,
)

//白天主题
private val LightColorPalette = AppColors(
    themeUi = themeColors[0],
    background = white,
    listItem = white3,
    divider = white4,
    textPrimary = black3,
    textSecondary = grey1,
    mainColor = white,
    card = white1,
    icon = white4,
    white = white4,
    warn = warn,
    success = green3,
    error = red2,
    primaryBtnBg = themeColors[0],
    secondBtnBg = white3,
    hot = red,
    placeholder = white3,
    transparent = Transparent,
)
var LocalHamColors = compositionLocalOf {
    LightColorPalette
}

@Stable
object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalHamColors.current

    enum class Theme {
        Light, Dark
    }
}

@Stable
class AppColors(
    themeUi: Color,
    background: Color,
    listItem: Color,
    divider: Color,
    textPrimary: Color,
    textSecondary: Color,
    mainColor: Color,
    card: Color,
    icon: Color,
    white: Color,
    warn: Color,
    success: Color,
    error: Color,
    primaryBtnBg: Color,
    secondBtnBg: Color,
    hot: Color,
    placeholder: Color,
    transparent:Color
) {
    var themeUi: Color by mutableStateOf(themeUi)
        internal set
    var background: Color by mutableStateOf(background)
        private set
    var listItem: Color by mutableStateOf(listItem)
        private set
    var divider: Color by mutableStateOf(divider)
        private set
    var textPrimary: Color by mutableStateOf(textPrimary)
        internal set
    var textSecondary: Color by mutableStateOf(textSecondary)
        private set
    var mainColor: Color by mutableStateOf(mainColor)
        internal set
    var card: Color by mutableStateOf(card)
        private set
    var icon: Color by mutableStateOf(icon)
        private set
    var white: Color by mutableStateOf(white)
        private set
    var warn: Color by mutableStateOf(warn)
        private set
    var success: Color by mutableStateOf(success)
        private set
    var error: Color by mutableStateOf(error)
        private set
    var primaryBtnBg: Color by mutableStateOf(primaryBtnBg)
        internal set
    var secondBtnBg: Color by mutableStateOf(secondBtnBg)
        private set
    var hot: Color by mutableStateOf(hot)
        private set
    var placeholder: Color by mutableStateOf(placeholder)
        private set
    var transparent: Color by mutableStateOf(transparent)
        private set

}


/**
 * 主题类型
 */
enum class ThemeType {
    Default,
    Theme1,
    Theme2,
    Theme3,
    Theme4,
    Theme5,
    DarkColorPalette
}
@Composable
fun ComposeAppTheme(
    themeType: ThemeType,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    val targetColors = when(themeType) {
        ThemeType.Default->{
            LightColorPalette.themeUi = themeColors[0]
            LightColorPalette
        }
        ThemeType.Theme1->{
            LightColorPalette.themeUi = themeColors[1]
            LightColorPalette
        }
        ThemeType.Theme2->{
            LightColorPalette.themeUi = themeColors[2]
            LightColorPalette
        }
        ThemeType.Theme3->{
            LightColorPalette.themeUi = themeColors[3]
            LightColorPalette
        }
        ThemeType.Theme4->{
            LightColorPalette.themeUi = themeColors[4]
            LightColorPalette
        }
        ThemeType.Theme5->{
            LightColorPalette.themeUi = themeColors[5]
            LightColorPalette
        }
        else -> {
            DarkColorPalette
        }
    }

    val themeUi = animateColorAsState(targetColors.themeUi, TweenSpec(600))
    val background = animateColorAsState(targetColors.background, TweenSpec(600))
    val listItem = animateColorAsState(targetColors.listItem, TweenSpec(600))
    val divider = animateColorAsState(targetColors.divider, TweenSpec(600))
    val textPrimary = animateColorAsState(targetColors.textPrimary, TweenSpec(600))
    val textSecondary = animateColorAsState(targetColors.textSecondary, TweenSpec(600))
    val mainColor = animateColorAsState(targetColors.mainColor, TweenSpec(600))
    val card = animateColorAsState(targetColors.card, TweenSpec(600))
    val icon = animateColorAsState(targetColors.icon, TweenSpec(600))
    val white = animateColorAsState(targetColors.white, TweenSpec(600))
    val warn = animateColorAsState(targetColors.warn, TweenSpec(600))
    val success = animateColorAsState(targetColors.success, TweenSpec(600))
    val error = animateColorAsState(targetColors.error, TweenSpec(600))
    val primaryBtnBg = animateColorAsState(targetColors.primaryBtnBg, TweenSpec(600))
    val secondBtnBg = animateColorAsState(targetColors.secondBtnBg, TweenSpec(600))
    val hot = animateColorAsState(targetColors.hot, TweenSpec(600))
    val placeholder = animateColorAsState(targetColors.placeholder, TweenSpec(600))
    val transparent = animateColorAsState(targetColors.transparent, TweenSpec(600))
    val hamColors = AppColors(
        themeUi = themeUi.value,
        background = background.value,
        listItem = listItem.value,
        divider = divider.value,
        textPrimary = textPrimary.value,
        textSecondary = textSecondary.value,
        mainColor = mainColor.value,
        card = card.value,
        icon = icon.value,
        primaryBtnBg = primaryBtnBg.value,
        secondBtnBg = secondBtnBg.value,
        white = white.value,
        warn = warn.value,
        success = success.value,
        error = error.value,
        hot = hot.value,
        placeholder = placeholder.value,
        transparent = transparent.value
    )

    val systemUiCtrl = rememberSystemUiController()
    //SideEffect用于屏蔽state，取消监听
    //SideEffect {
    systemUiCtrl.setStatusBarColor(hamColors.themeUi)
    systemUiCtrl.setNavigationBarColor(hamColors.themeUi)
    systemUiCtrl.setSystemBarsColor(hamColors.themeUi)
    //}

    ProvideWindowInsets {
        CompositionLocalProvider(LocalHamColors provides hamColors, content = content)
    }

}