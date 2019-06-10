package com.giantbing.chatlib.repository

import com.giantbing.mvvmbase.Base.BaseRepository
import com.netease.nimlib.sdk.auth.LoginInfo

abstract class NimBaseRepository : BaseRepository() {
    sealed class CallBack {
        class SUCCESS<T>(val value: T) : CallBack()
        class FAILED(val code: Int) : CallBack()
        class ERROR(val exception: Throwable?) : CallBack()
    }
}