package com.giantbing.mvvmbase.Base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {
    protected lateinit var mViewModel: VM
    //private var mDialog: MaterialDialog? = null
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


    protected fun showProgressDialog(content: String) {
//        if (mDialog == null)
//            mDialog = MaterialDialog.Builder(this)
//                .content(content).progress(true, 1)
//                .canceledOnTouchOutside(false).build()
//        else
//            mDialog?.setContent(content)
//        mDialog?.show()
    }

    protected fun showProgressDialog(resId: Int) {
//        if (mDialog == null)
//            mDialog = MaterialDialog.Builder(this)
//                .content(getString(resId)).progress(true, 1)
//                .canceledOnTouchOutside(false).build()
//        else
//            mDialog?.setContent(getString(resId))
//        mDialog?.show()
    }

    protected fun dismissProgressDialog() {
        // mDialog?.dismiss()
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