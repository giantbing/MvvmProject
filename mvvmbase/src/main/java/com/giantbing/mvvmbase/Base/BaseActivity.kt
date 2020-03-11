package com.giantbing.mvvmbase.Base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var mViewModel: VM
    //private var mDialog: MaterialDialog? = null
    private val waitDialog  by lazy { WaitDialog() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initVM()
        initView()
        initData()
        bindView()
        bindClick()
    }


    private fun initVM() {
        mViewModel = ViewModelProviders.of(this).get(providerVMClass())
        lifecycle.addObserver(mViewModel)
    }


    protected fun showProgressDialog() {
        waitDialog.show(this.supportFragmentManager,"")
    }


    protected fun dismissProgressDialog() {

        waitDialog.dismiss()
    }

    abstract fun providerVMClass(): Class<VM>
    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun bindView()
    abstract fun initData()
    abstract fun bindClick()
    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }
}