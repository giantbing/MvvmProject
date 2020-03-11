package com.giantbing.mvvmbase.Base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mqq.ssby.pyx.base.FatherFragment

abstract class BaseFragment<VM : BaseViewModel> : FatherFragment() {
    protected lateinit var mViewModel: VM
    private val waitDialog  by lazy { WaitDialog() }



    override fun onCreateFragment(contentView: View?) {
        initVM()
        initView()
        initData()
        startObserve()
    }


    open fun startObserve() {}
    abstract fun providerVMClass(): Class<VM>


    abstract fun initView()

    abstract fun initData()

    private fun initVM() {
        providerVMClass().let {
//            ViewModelProvider(this).get(it)
            mViewModel = ViewModelProviders.of(this).get(it)

            lifecycle.addObserver(mViewModel)
        }
    }

    protected fun showProgressDialog() {
        waitDialog.show(requireActivity().supportFragmentManager,"")
    }


    protected fun dismissProgressDialog() {

        waitDialog.dismiss()
    }
    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }
}