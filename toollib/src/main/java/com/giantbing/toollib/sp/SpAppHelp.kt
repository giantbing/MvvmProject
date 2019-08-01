package com.giantbing.toollib.sp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences


/**
 * Created by Administrator on 2018/3/2 0002.
 * 保存app里的内容会经常清除的
 */
object SpAppHelp {

    private lateinit var edit: SharedPreferences.Editor
    private lateinit var sharedPreferences: SharedPreferences


    //在Application初始化
    @SuppressLint("CommitPrefEdits")
    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(SpConfig.SPAPP+context.packageName, Context.MODE_PRIVATE)
        edit = sharedPreferences.edit()
    }


    fun putString(key: String, value: String) = edit.putString(key, value).commit()

    fun putBoolean(key: String, value: Boolean) = edit.putBoolean(key, value).commit()

    fun putInt(key: String, value: Int) = edit.putInt(key, value).commit()


    //必不为空
    fun getStringEmpty(key: String): String = sharedPreferences.getString(key, null)

    //可为空
    fun getString(key: String): String? = sharedPreferences.getString(key, null)


    fun getBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    fun clearAll() = edit.clear().commit()

}