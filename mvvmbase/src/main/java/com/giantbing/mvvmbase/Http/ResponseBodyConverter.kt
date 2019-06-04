package com.giantbing.mvvmbase.Http

import com.giantbing.mvvmbase.Base.CodeException
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import okhttp3.internal.Util.UTF_8
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Converter
import java.io.ByteArrayInputStream
import java.io.InputStreamReader

class ResponseBodyConverter<T>(private val adapter: TypeAdapter<T>) : Converter<ResponseBody, T> {


    override fun convert(value: ResponseBody): T? {
        val gson = Gson()
        var code = 0
        var msg = ""
        val response = value.string()
        try {
            val jsonObject = JSONObject(response)
            code = jsonObject.getInt("code")
            msg = jsonObject.getString("msg")
        } catch (e: JSONException) {
            throw CodeException(-1, "转换code或msg失败")
        }
        val mediaType = value.contentType()
        val charset = mediaType?.charset(UTF_8) ?: UTF_8

        val inputStream = ByteArrayInputStream(response.toByteArray())
        val jsonReader = gson.newJsonReader(InputStreamReader(inputStream, charset))

        if (code != 200) {
            value.close()
            throw CodeException(code, msg)
        }
        value.use {
            return adapter.read(jsonReader)
        }
    }

}