package com.giantbing.mvvmbase.Http

import com.giantbing.mvvmbase.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseRetrofitClient {

    abstract fun getTimeOut(): Int
    abstract fun otherHanleBuilder(builder: OkHttpClient.Builder)
    abstract fun getHeader(): MutableMap<String, String>
    //使用默认的GsonConverterFactory

    abstract fun getConverterFactory(): Converter.Factory

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }
            builder.addInterceptor(logging)
                .addInterceptor(HeadInterCepteor(getHeader()))
                .connectTimeout(getTimeOut().toLong(), TimeUnit.SECONDS)
                .readTimeout(getTimeOut().toLong(), TimeUnit.SECONDS)

            return builder.build()
        }

    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(getConverterFactory())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }
}