package com.giantbing.mvvmproject.model.api

import com.giantbing.mvvmproject.model.bean.test.LoginBean
import io.reactivex.Observable
import retrofit2.http.*

interface Service {
    companion object{
        const val BASE_URL = "https://jm.ssby66.com/"
    }
    //登录
    @FormUrlEncoded
    @POST("v1/userexpose/login")
    fun login(@Field("phone") phone: String
              , @Field("pwd") pwd: String
              , @Field("province") province: String?
              , @Field("city") city: String?): Observable<LoginBean>
}