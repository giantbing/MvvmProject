package com.giantbing.chatlib.live

import com.netease.nimlib.sdk.NIMClient
import com.netease.nimlib.sdk.Observer
import com.netease.nimlib.sdk.msg.MsgServiceObserve
import com.netease.nimlib.sdk.msg.model.RecentContact

//监听最近会话变更
object NimContactObs : BaseLive {
    private val msgService by lazy { NIMClient.getService(MsgServiceObserve::class.java) }

    private var recentContactCallBack: (List<RecentContact>) -> Unit = {}

    //最近会话回调
    private object RecentContactObs : Observer<List<RecentContact>> {

        override fun onEvent(t: List<RecentContact>) {
            recentContactCallBack(t)
        }
    }

    fun setRecentContactCallBack(block: (List<RecentContact>) -> Unit) {
        this.recentContactCallBack = block
    }


    override fun doRegist() {
        msgService.observeRecentContact(RecentContactObs, true)
    }

    override fun onDestory() {
        msgService.observeRecentContact(RecentContactObs, false)
    }
}