package com.giantbing.chatlib.except

object NimException {


    fun handleLoginException(code: Int): String {
        return when (code) {
            302 -> "账号或密码错误"
            408 -> "Im网络超时"
            415 -> "Im网络超时"
            416 -> "Im网络超时"
            417 -> "已被挤下线"
            else -> "im登录出错：$code"
        }
    }

}