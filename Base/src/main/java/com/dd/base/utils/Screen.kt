package com.dd.base.utils

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
/*
* 屏幕适配方案
* 屏幕宽度已1080为标准。
* */
val Number.sdp
    get() =
        Resources.getSystem().displayMetrics.run {
            val dp = widthPixels / density
            Dp((toFloat() * dp / 1080))
        }

