package com.giantbing.chatlib.repository

import com.netease.nimlib.sdk.RequestCallback
import com.netease.nimlib.sdk.RequestCallbackWrapper
import com.netease.nimlib.sdk.msg.model.IMMessage
import com.netease.nimlib.sdk.msg.model.QueryDirectionEnum
import io.reactivex.Observable

class NimHistoryRepository : NimBaseRepository() {
    //获取本地历史记录
    fun getLocalHistory(anchor: IMMessage): Observable<WrapperCallBack<List<IMMessage>>> {
        return Observable.create {
            msgService.queryMessageListEx(anchor, QueryDirectionEnum.QUERY_OLD, 20, true)
                .setCallback(object : RequestCallbackWrapper<List<IMMessage>>() {
                    override fun onResult(code: Int, result: List<IMMessage>, exception: Throwable?) {
                        it.onNext(WrapperCallBack(code, result, exception))
                    }

                })
        }

    }

    //获取服务器历史记录
    fun getRemoteHistory(anchor: IMMessage): Observable<CallBack> {
        return Observable.create<CallBack> {
            msgService.pullMessageHistory(anchor, 20, false).setCallback(object : RequestCallback<List<IMMessage>> {
                override fun onSuccess(param: List<IMMessage>) {
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


}