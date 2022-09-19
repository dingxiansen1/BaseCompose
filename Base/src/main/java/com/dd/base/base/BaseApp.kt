package com.dd.base.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

open class BaseApp :Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        mContext=this
    }
}