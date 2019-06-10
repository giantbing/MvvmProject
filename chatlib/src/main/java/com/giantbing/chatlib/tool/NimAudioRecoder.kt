package com.giantbing.chatlib.tool

import com.giantbing.mvvmbase.Base.BaseApp
import com.netease.nimlib.sdk.media.record.AudioRecorder
import com.netease.nimlib.sdk.media.record.IAudioRecordCallback
import com.netease.nimlib.sdk.media.record.RecordType
import java.io.File
import kotlin.properties.Delegates

class NimAudioRecoder : IAudioRecordCallback {

    init {
        recoder =  AudioRecorder(BaseApp.appContext,RecordType.AAC,MAX_DURATION,this)
    }

    companion object{
        var MAX_DURATION = 120
        var recoder:AudioRecorder by Delegates.notNull()
    }

    override fun onRecordSuccess(audioFile: File, audioLength: Long, recordType: RecordType) {
        // 录音结束，成功

    }

    override fun onRecordReachedMaxTime(maxTime: Int) {
        // 到达指定的最长录音时间
    }

    override fun onRecordReady() {
        // 初始化完成回调，提供此接口用于在录音前关闭本地音视频播放（可选）

    }

    override fun onRecordCancel() {
        // 录音结束， 用户主动取消录音
    }

    override fun onRecordStart(audioFile: File, recordType: RecordType) {
        // 开始录音回调
    }

    override fun onRecordFail() {
        // 录音结束，出错
    }


    fun startRecord(){
         recoder.startRecord()
    }
    fun completeRecord(isCancel:Boolean){
        recoder.completeRecord(isCancel)
    }

}