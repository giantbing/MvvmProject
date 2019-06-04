package com.giantbing.mvvmproject.ui.main

import androidx.lifecycle.MutableLiveData
import com.giantbing.mvvmbase.Base.BaseViewModel
import com.giantbing.mvvmproject.model.bean.TestHelloBean
import com.giantbing.mvvmproject.model.repository.TestHelloRepository

class MainViewModel : BaseViewModel() {
    val mHelloMsg: MutableLiveData<String> = MutableLiveData()

    fun getMsg(msg: String) {
        api(TestHelloRepository().getMsg(msg), {
            mHelloMsg.value = it.data
        }, {
            mHelloMsg.value = it.msg
        })
    }
}