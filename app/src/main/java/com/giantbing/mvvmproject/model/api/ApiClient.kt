package com.giantbing.mvvmproject.model.api

import com.giantbing.mvvmbase.Http.BaseRetrofitClient
import com.giantbing.mvvmbase.Http.ConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Converter

object ApiClient : BaseRetrofitClient() {
    val api by lazy { getService(Service::class.java, Service.BASE_URL) }
    override fun getConverterFactory(): Converter.Factory = ConverterFactory()

    override fun getTimeOut(): Int = 10

    override fun otherHandleBuilder(builder: OkHttpClient.Builder) {

    }

    override fun getHeader(): MutableMap<String, String> = mutableMapOf(
            Pair("os", "android"),
            Pair("channels", "pugongying"),
            Pair("waistcoat", "JM"),
            Pair("appversion", "1.8.1"))

}