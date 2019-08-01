package com.giantbing.mvvmproject.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.giantbing.mvvmbase.Base.BaseActivity
import com.giantbing.mvvmproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {



    override fun providerVMClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {
        tvBtn.setOnClickListener {
           // mViewModel.getMsg("hahahhah")
        }
    }

    override fun bindView() {
        mViewModel.apply {
//            mHelloMsg.observe(this@MainActivity, Observer {
//                tvMsg.text = it
//            })
        }
    }

    override fun initData() {
    }
    override fun bindClick() {

    }
}
