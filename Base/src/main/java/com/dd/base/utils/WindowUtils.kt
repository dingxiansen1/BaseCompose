package com.dd.base.utils

import android.app.Activity
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
/**
 * 设置Window导航栏的工具类
 * */
object WindowUtils {

    var window :Window?=null

    fun Init(activity: Activity){
        window= activity.window
    }
    /**
     * 全屏隐藏系统栏，如：你看视频或者玩游戏的时候，就可以通过此种方式，体验是一样的
     * */
    fun hideSystemUI() {
        window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
            // 如果你在Composable里面，可以参考rememberSystemUiController() 一样使用LocalView.current也可以
            WindowInsetsControllerCompat(it, it.decorView).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())
                // 修改行为
                controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
    /**
     * 从全屏隐藏状态下，恢复系统栏的显示
     * */
    fun showSystemUI() {
        window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, true)
            WindowInsetsControllerCompat(it, it.decorView).show(WindowInsetsCompat.Type.systemBars())
        }
    }
}