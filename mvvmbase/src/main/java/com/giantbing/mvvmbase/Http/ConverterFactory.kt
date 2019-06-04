package com.giantbing.mvvmbase.Http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ConverterFactory : Converter.Factory() {

    private val gson by lazy { Gson() }
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {

        return ResponseBodyConverter(gson.getAdapter(TypeToken.get(type)))
    }
}