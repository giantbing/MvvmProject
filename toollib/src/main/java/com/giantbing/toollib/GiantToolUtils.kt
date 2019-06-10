package com.giantbing.toollib

import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import es.dmoral.toasty.Toasty
import kotlin.properties.Delegates

object GiantToolUtils {
    var context:Context by Delegates.notNull()
    fun init(context: Context) {
        this.context = context
        //log
        Logger.addLogAdapter(AndroidLogAdapter())
        //toast
        Toasty.Config.getInstance()
//            .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)
//            .setToastTypeface( Typeface typeface) // optional
//            .setTextSize(int sizeInSp) // optional
            .allowQueue(true) // optional (prevents several Toastys from queuing)
            .apply()
    }
}