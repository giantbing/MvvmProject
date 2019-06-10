package com.giantbing.chatlib.repository

import com.netease.nimlib.sdk.NIMClient
import com.netease.nimlib.sdk.RequestCallbackWrapper
import com.netease.nimlib.sdk.msg.MsgService
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum
import com.netease.nimlib.sdk.msg.model.RecentContact
import io.reactivex.Observable

object NimContactRepository : NimBaseRepository() {


    //获取最近会话列表
    fun getRecentContact(): Observable<WrapperCallBack<List<RecentContact>>> {
     return Observable.create<WrapperCallBack<List<RecentContact>>> {
          msgService.queryRecentContacts().setCallback(object : RequestCallbackWrapper<List<RecentContact>>() {
              override fun onResult(code: Int, result: List<RecentContact>?, exception: Throwable?) {
                it.onNext(WrapperCallBack(code,result,exception))
              }

          })
      }
    }

    //获取未读数
    fun getUnreadCount():Observable<Int>{
        return Observable.create {
            it.onNext(msgService.totalUnreadCount)
        }
    }

    //设置该用户消息都是已读
    fun setClearUnread(accId:String) {
       msgService.clearUnreadCount(accId, CHAT_TYPE_P2P)
    }
    //删除最近对话列表
    fun deleteRecentContact(recent:RecentContact){
        msgService.deleteRecentContact(recent)
    }


}