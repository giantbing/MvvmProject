package com.giantbing.chatlib.repository

import com.giantbing.mvvmbase.Base.BaseRepository
import com.netease.nimlib.sdk.NIMClient
import com.netease.nimlib.sdk.RequestCallback
import com.netease.nimlib.sdk.auth.AuthService
import com.netease.nimlib.sdk.auth.LoginInfo
import io.reactivex.Completable
import io.reactivex.Observable
//负责登录登出
object NimLoginRepository : NimBaseRepository() {


    data class NimLoginObs(
        val state: CallBack,
        val param: LoginInfo?,
        val exception: Throwable? = null,
        val code: Int = 0
    )

    fun doLogin(info: LoginInfo): Observable<CallBack> {
        return Observable.create<CallBack> {
            NIMClient.getService(AuthService::class.java).login(info)
                .setCallback(object : RequestCallback<LoginInfo> {
                    override fun onSuccess(param: LoginInfo) {
                        it.onNext(CallBack.SUCCESS(param))
                    }

                    override fun onFailed(code: Int) {
                        it.onNext(CallBack.FAILED(code))
                    }

                    override fun onException(exception: Throwable?) {
                        it.onNext(CallBack.ERROR(exception))
                    }
                })

        }


    }
    fun doLogOut():Completable{
        return Completable.create {
            NIMClient.getService(AuthService::class.java).logout()
            it.onComplete()
        }
    }


}