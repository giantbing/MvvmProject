package com.giantbing.mvvmbase.Base

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

abstract class BaseApp:Application() {
    companion object{
        var appContext :Context by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        initCreate()
    }
    abstract fun initCreate()

}