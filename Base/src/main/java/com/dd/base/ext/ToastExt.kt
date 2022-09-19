package com.dd.base.ext

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.widget.Toast
import com.dd.base.base.BaseApp

private var mToast: Toast? = null

/**
 * 显示时间较短的吐司
 *
 * @param text String，显示的内容
 */
fun showToast(text: String?) {
    showToast(context = BaseApp.mContext, text = text)
}

fun showToast(context: Context = BaseApp.mContext, text: String?) {
    if (TextUtils.isEmpty(text)) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, text, Toast.LENGTH_SHORT)
    } else {
        Handler(context.mainLooper).post { showToast(context, text, Toast.LENGTH_SHORT) }
    }
}


/**
 * 显示时间较长的吐司
 *
 * @param text String，显示的内容
 */
fun showLongToast(context: Context? = BaseApp.mContext, text: String?) {
    if (context == null || TextUtils.isEmpty(text)) return
    if (Thread.currentThread() === Looper.getMainLooper().thread) {
        showToast(context, text, Toast.LENGTH_LONG)
    } else {
        Handler(context.mainLooper).post { showToast(context, text, Toast.LENGTH_LONG) }
    }
}

private fun showToast(context: Context? = BaseApp.mContext, text: String?, duration: Int) {
    if (TextUtils.isEmpty(text)) return
    cancelToast()
    if (mToast == null) {
        mToast = Toast.makeText(context, null as CharSequence?, duration)
    }
    mToast?.apply {
        setText(text)
        this.duration = duration
        show()
    }
}

fun cancelToast() {
    mToast?.cancel()
}




