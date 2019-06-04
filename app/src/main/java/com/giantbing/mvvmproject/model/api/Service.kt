package com.giantbing.mvvmproject.model.api

import com.giantbing.mvvmproject.model.bean.TestHelloBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    companion object{
        const val BASE_URL = "http://192.168.100.172:8080/"
    }
    @GET("test/hello")
    fun getTest(@Query("msg") msg:String):Observable<TestHelloBean>

}