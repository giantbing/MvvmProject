package com.giantbing.chatlib.tool

import android.media.AudioManager
import com.giantbing.mvvmbase.Base.BaseApp
import com.netease.nimlib.sdk.media.player.AudioPlayer
import com.netease.nimlib.sdk.media.player.OnPlayListener
import kotlin.properties.Delegates

class NimAudioPlayer(filePath: String) : OnPlayListener {
    init {
        player = AudioPlayer(BaseApp.appContext, filePath, this)
    }

    companion object {
        var streamType = AudioManager.STREAM_MUSIC//默认扬声器播放
        var player: AudioPlayer by Delegates.notNull()
    }

    override fun onPrepared() {
        // 音频转码解码完成，会马上开始播放了
    }

    override fun onCompletion() {
        // 播放结束
    }

    override fun onInterrupt() {
        // 播放被中断了
    }

    override fun onError(error: String?) {
        // 播放过程中出错。参数为出错原因描述
    }

    override fun onPlaying(curPosition: Long) {
        // 播放进度报告，每隔 500ms 会回调一次，告诉当前进度。 参数为当前进度，单位为毫秒，可用于更新 UI
    }


    fun startPlay() {
        player.start(streamType)
    }

    fun stop() {
        player.stop()
    }
}