package com.giantbing.chatlib.Config

import com.netease.nimlib.sdk.SDKOptions
import android.R
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import com.netease.nimlib.sdk.StatusBarNotificationConfig
import com.netease.nimlib.sdk.NosTokenSceneConfig




object SdkConfig {
    var notifyActivity: Class<AppCompatActivity>? = null
    var notifyDrawable: Int = 0

    //设置通知小图标
    fun setNotify(notifyActivity: Class<AppCompatActivity>, notifyDrawable: Int) {
        this.notifyActivity = notifyActivity
        this.notifyDrawable = notifyDrawable
    }

    fun getNimSdkOptions(context: Context): SDKOptions {

        val options = SDKOptions()

        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
        val config = StatusBarNotificationConfig()
        notifyActivity?.let {
            config.notificationEntrance = it // 点击通知栏跳转到该Activity
            config.notificationSmallIconId = notifyDrawable
        }


        // 呼吸灯配置
        config.ledARGB = Color.GREEN
        config.ledOnMs = 1000
        config.ledOffMs = 1500
        // 通知铃声的uri字符串
        config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg"
        options.statusBarNotificationConfig = config
        // 配置保存图片，文件，log 等数据的目录
        // 如果 options 中没有设置这个值，SDK 会使用采用默认路径作为 SDK 的数据目录。
        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
        val sdkPath = context.cacheDir.absolutePath + "/nim"
        options.sdkStorageRootPath = sdkPath
        options.preloadAttach = true
        // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
        // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
        options.mNosTokenSceneConfig = createNosTokenScene()
        return options
    }
    private fun createNosTokenScene(): NosTokenSceneConfig {
        val nosTokenSceneConfig = NosTokenSceneConfig()

        //更新默认场景（NimNosSceneKeyConstant.NIM_DEFAULT_IM）对应的过期时间(天)
        nosTokenSceneConfig.updateDefaultIMSceneExpireTime(30)

        return nosTokenSceneConfig
    }
}