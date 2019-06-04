package com.giantbing.mvvmbase.Http

import okhttp3.Interceptor
import okhttp3.Response

class HeadInterCepteor(private val headerMap:MutableMap<String,String>):Interceptor {



    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        headerMap.forEach {
            builder.addHeader(it.key,it.value)
        }
        return chain.proceed(builder.build())
    }
}