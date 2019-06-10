package com.giantbing.chatlib.repository

import com.netease.nimlib.sdk.msg.MsgService
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum


class NimChatRoomRepository : NimBaseRepository() {

    //进入聊天界面 onResume中 设置当前聊天对象
    fun setChattingAccount(accid: String) {
        msgService.setChattingAccount(accid, CHAT_TYPE_P2P)
    }
    //退出聊天界面时
    fun closeChattingRoom() {
        msgService.setChattingAccount(MsgService.MSG_CHATTING_ACCOUNT_NONE, SessionTypeEnum.None)
    }

}