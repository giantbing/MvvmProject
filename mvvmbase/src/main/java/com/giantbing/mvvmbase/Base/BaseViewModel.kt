package com.giantbing.mvvmbase.Base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException


abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    private val disposables = CompositeDisposable()


    fun <T : Any> api(api: Observable<T>, successBlock: (T) -> Unit, errorBlock: (ErrorData) -> Unit): Disposable {
        return api.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    disposables.add(it)
                }
                .subscribe({
                    successBlock.invoke(it)
                }, {
                    errorBlock.invoke(handleError(it))
                })
    }

    private fun handleError(t: Throwable): ErrorData {
        return ExceptionHandle.exceptionMessage(t)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}

object ExceptionHandle {
    val network_error = "网络错误,请重试"
    fun exceptionMessage(e: Throwable): ErrorData {
        return if (e is CodeException) ErrorData(e.code, e.msg) else ErrorData(0, network_error)
    }
}

data class ErrorData(val code: Int, val msg: String)
data class CodeException(var code: Int, var msg: String) : IOException()