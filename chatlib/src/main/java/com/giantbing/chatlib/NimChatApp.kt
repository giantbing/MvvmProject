package com.giantbing.chatlib

import android.content.Context
import com.giantbing.chatlib.Config.SdkConfig
import com.netease.nimlib.sdk.NIMClient

object NimChatApp {
    fun init(context: Context) {
        NIMClient.init(context, null, SdkConfig.getNimSdkOptions(context))
    }

}