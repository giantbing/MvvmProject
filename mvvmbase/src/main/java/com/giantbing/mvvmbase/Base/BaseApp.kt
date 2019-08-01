package com.giantbing.mvvmbase.Base

import android.app.Application
import android.content.Context
import com.giantbing.toollib.GiantToolUtils
import com.giantbing.toollib.Log.GiantLog
import kotlin.properties.Delegates

abstract class BaseApp:Application() {
    companion object{
        var appContext :Context by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        init()
        initCreate()
    }
    abstract fun initCreate()
    private fun init(){
        GiantToolUtils.init(appContext)
    }
}