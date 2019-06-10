package com.giantbing.toollib.Toast

import android.widget.Toast
import com.giantbing.toollib.GiantToolUtils
import es.dmoral.toasty.Toasty

object GiantToast {
    fun error(msg:String){
        Toasty.error(GiantToolUtils.context,msg,Toast.LENGTH_SHORT).show()
    }
    fun success(msg: String){
        Toasty.success(GiantToolUtils.context,msg,Toast.LENGTH_SHORT).show()
    }
    fun info(msg: String){
        Toasty.info(GiantToolUtils.context,msg,Toast.LENGTH_SHORT).show()
    }
    fun warning(msg: String){
        Toasty.warning(GiantToolUtils.context,msg,Toast.LENGTH_SHORT).show()
    }
    fun normal(msg: String){
        Toasty.normal(GiantToolUtils.context,msg,Toast.LENGTH_SHORT).show()
    }
}