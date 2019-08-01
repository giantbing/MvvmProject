package com.giantbing.mvvmproject.ui.test

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.giantbing.mvvmbase.Base.BaseViewModel
import com.giantbing.mvvmproject.model.bean.test.LoginBean
import com.giantbing.mvvmproject.model.repository.Test.TestLoginRepositroy
import com.giantbing.toollib.Toast.GiantToast

class TestLoginViewModel:BaseViewModel() {
    val loginInfo = MutableLiveData<LoginBean>()
    fun doLogin(phone:String,pwd:String){
        api(TestLoginRepositroy().dologin(phone, pwd),{
            loginInfo.value = it
        },{
            GiantToast.error(it.msg)
        })
    }
}