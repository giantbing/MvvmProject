package com.giantbing.chatlib.repository

import com.giantbing.mvvmbase.Base.BaseRepository
import com.netease.nimlib.sdk.NIMClient
import com.netease.nimlib.sdk.msg.MsgService
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum
import com.netease.nimlib.sdk.msg.model.RecentContact

abstract class NimBaseRepository : BaseRepository() {
    protected val CHAT_TYPE_P2P = SessionTypeEnum.P2P
    val msgService: MsgService by lazy { NIMClient.getService(MsgService::class.java) }

    sealed class CallBack {
        class SUCCESS<T>(val value: T) : CallBack()
        class FAILED(val code: Int) : CallBack()
        class ERROR(val exception: Throwable?) : CallBack()
    }

    data class WrapperCallBack<T>(val code: Int, val result: T?,val  exception: Throwable?)
}