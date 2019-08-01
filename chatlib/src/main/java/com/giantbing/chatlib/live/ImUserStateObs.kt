package com.giantbing.chatlib.live

import com.giantbing.toollib.Log.GiantLog
import com.giantbing.toollib.Toast.GiantToast
import com.netease.nimlib.sdk.NIMClient
import com.netease.nimlib.sdk.Observer
import com.netease.nimlib.sdk.StatusCode
import com.netease.nimlib.sdk.auth.AuthServiceObserver
import com.netease.nimlib.sdk.auth.constant.LoginSyncStatus
//负责登录后 监听是否在同步数据和  用户状态变更
object ImUserStateObs : BaseLive, Observer<StatusCode> {

    //是否在同步数据
    object ImSyncstatus : Observer<LoginSyncStatus> {
        override fun onEvent(t: LoginSyncStatus?) {
            isSync = when (t) {
                LoginSyncStatus.BEGIN_SYNC -> true
                LoginSyncStatus.SYNC_COMPLETED -> false
                else -> false
            }
            syncBlock.invoke(isSync)

        }

    }

    private var outLoginBlock = {}
    private var syncBlock: (Boolean) -> Unit = {}
    var isSync = false

    fun setSyncBlock(block: (Boolean) -> Unit) {
        this.syncBlock = block
    }

    fun setOutLoginBlock(block: () -> Unit) {
        this.outLoginBlock = block
    }

    override fun onEvent(status: StatusCode?) {
        when (status) {
            StatusCode.UNLOGIN -> loginError()
            StatusCode.NET_BROKEN -> netBroken()
            StatusCode.CONNECTING -> connecting()
            StatusCode.LOGINING -> loginLoading()
            StatusCode.SYNCING -> synchronizedData()
            StatusCode.KICKOUT -> userLoginTopOff()
            StatusCode.FORBIDDEN -> accountBanned()
            StatusCode.VER_ERROR -> clientVersions()
            StatusCode.LOGINED -> loginSucceed()
            StatusCode.KICK_BY_OTHER_CLIENT -> userLoginTopOff()
        }
    }

    //登录成功
    private fun loginSucceed() {
        GiantLog.d("IMData:重连成功")
    }

    //客户端版本不对
    private fun clientVersions() {
        GiantLog.d("IMData:" + "客户端版本不对")
    }

    //账号被禁用
    private fun accountBanned() {
        GiantToast.error("账号被禁用")
        outLoginBlock.invoke()
    }

    //在其他手机被顶掉
    private fun userLoginTopOff() {
        GiantToast.error("该账号在其他手机登录，如非本人操作，请及时修改密码")
        outLoginBlock.invoke()
    }

    //正在同步数据
    private fun synchronizedData() {
        GiantLog.d("IMData:" + "正在同步数据")
    }

    //登录中
    private fun loginLoading() {
        GiantLog.d("IMData:" + "登录中")
    }

    //正在连接服务器
    private fun connecting() {
        GiantLog.d("IMData:" + "正在连接服务器")
    }


    //网络连接断开
    private fun netBroken() {
        GiantLog.d("IMData:" + "网络连接断开")
    }


    //未登录、登录失败
    private fun loginError() {
        GiantLog.d("IMData:" + "未登录、登录失败")
    }

    override fun doRegist() {
        //登录时调用
        NIMClient.getService(AuthServiceObserver::class.java).observeOnlineStatus(this, true)
        NIMClient.getService(AuthServiceObserver::class.java).observeLoginSyncDataStatus(ImSyncstatus, true)
    }

    override fun onDestory() {
        //注销时调用
        NIMClient.getService(AuthServiceObserver::class.java).observeOnlineStatus(this, false)
        NIMClient.getService(AuthServiceObserver::class.java).observeLoginSyncDataStatus(ImSyncstatus, false)
    }
}