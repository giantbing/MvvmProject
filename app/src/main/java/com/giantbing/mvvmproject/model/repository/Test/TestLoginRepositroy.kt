package com.giantbing.mvvmproject.model.repository.Test

import com.giantbing.mvvmbase.Base.BaseRepository
import com.giantbing.mvvmproject.model.api.ApiClient
import com.giantbing.mvvmproject.model.bean.test.LoginBean
import io.reactivex.Observable

class TestLoginRepositroy:BaseRepository() {
    fun dologin(phone:String,pwd:String):Observable<LoginBean>{
        return ApiClient.api.login(phone,pwd,"四川", "成都")
    }
}