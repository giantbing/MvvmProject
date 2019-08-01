package com.giantbing.mvvmproject.ui.test

import androidx.lifecycle.Observer
import com.giantbing.mvvmbase.Base.BaseActivity
import com.giantbing.mvvmproject.R
import com.giantbing.toollib.Log.GiantLog
import kotlinx.android.synthetic.main.activity_test_login.*

class TestLoginActivity : BaseActivity<TestLoginViewModel>() {
    override fun providerVMClass(): Class<TestLoginViewModel> = TestLoginViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_test_login

    override fun initView() {

    }

    override fun bindView() {
        mViewModel.apply {
            loginInfo.observe(this@TestLoginActivity, Observer {
                GiantLog.e(it.toString())
            })
        }
    }

    override fun initData() {

    }

    override fun bindClick() {
        tvLoginBtn.setOnClickListener {
            mViewModel.doLogin(etPhone.text.toString(), etPwd.text.toString())
        }
    }

}