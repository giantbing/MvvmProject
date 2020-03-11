package com.giantbing.mvvmbase.Base

import com.giantbing.mvvmbase.Base.BaseDialogFragment
import com.giantbing.mvvmbase.Base.BaseViewModel
import com.giantbing.mvvmbase.R

class WaitDialog : BaseDialogFragment<TransFormDialogViewModel>() {
    override fun getLayoutResId(): Int = R.layout.dialog_wait

    override fun initView() {
    }

    override fun initData() {
    }

    override fun providerVMClass(): Class<TransFormDialogViewModel> =
        TransFormDialogViewModel::class.java


}

class TransFormDialogViewModel : BaseViewModel()