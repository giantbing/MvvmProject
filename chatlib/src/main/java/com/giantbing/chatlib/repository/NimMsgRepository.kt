package com.giantbing.chatlib.repository

import android.util.Log
import com.netease.nimlib.sdk.NIMClient
import com.netease.nimlib.sdk.RequestCallback
import com.netease.nimlib.sdk.msg.MessageBuilder
import com.netease.nimlib.sdk.msg.MsgService
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum
import com.netease.nimlib.sdk.msg.model.IMMessage
import io.reactivex.Observable
import java.io.File


object NimMsgRepository : NimBaseRepository() {




    //发送文本消息
    fun sentStringMsg(accid: String, msg: String): Observable<CallBack> {
        val imMsg = MessageBuilder.createTextMessage(accid, CHAT_TYPE_P2P, msg)
        return doSendImMsg(imMsg)
    }
    //发送图片消息
    fun sentPicMsg(accid: String,img:File): Observable<CallBack>{
        val imgMsg = MessageBuilder.createImageMessage(accid, CHAT_TYPE_P2P,img)
        return doSendImMsg(imgMsg)
    }
    //发送视屏消息
    fun sentVideoMsg(accid: String,video:File,duration: Long,width:Int,height:Int): Observable<CallBack>{
        val imgMsg = MessageBuilder.createVideoMessage(accid, CHAT_TYPE_P2P,video,duration,width,height,null)
        return doSendImMsg(imgMsg)
    }
    //发送音频消息
    fun sentAudioMsg(accid: String,audio:File,duration: Long): Observable<CallBack>{
        val imgMsg = MessageBuilder.createAudioMessage(accid, CHAT_TYPE_P2P,audio,duration)
        return doSendImMsg(imgMsg)
    }



    private fun doSendImMsg(msg: IMMessage): Observable<CallBack> {
        return Observable.create<CallBack> {
            msgService.sendMessage(msg, false).setCallback(object : RequestCallback<Void> {
                override fun onSuccess(param: Void?) {
                    it.onNext(CallBack.SUCCESS(param))
                }

                override fun onFailed(code: Int) {
                    it.onNext(CallBack.FAILED(code))
                }

                override fun onException(exception: Throwable?) {
                    it.onNext(CallBack.ERROR( exception))
                }

            })
        }

    }
}