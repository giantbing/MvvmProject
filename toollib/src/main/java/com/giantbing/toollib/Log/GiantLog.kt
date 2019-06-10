package com.giantbing.toollib.Log

import com.orhanobut.logger.Logger

object GiantLog {
    fun e(msg:String){
        Logger.e(msg)
    }
    fun d(msg:Any){
        Logger.d(msg)
    }
    fun i(msg:String,obj:Any){
        Logger.i(msg,obj)
    }
    fun json(json:String){
        Logger.json(json)
    }
}