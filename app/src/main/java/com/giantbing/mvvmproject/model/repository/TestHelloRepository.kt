package com.giantbing.mvvmproject.model.repository

import com.giantbing.mvvmbase.Base.BaseRepository
import com.giantbing.mvvmproject.model.api.ApiClient
import com.giantbing.mvvmproject.model.bean.TestHelloBean
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class TestHelloRepository : BaseRepository() {

    fun getMsg(msg:String):Observable<TestHelloBean>{
        return ApiClient.api.getTest(msg)
    }

}