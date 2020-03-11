package com.giantbing.mvvmbase.Base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseDialogFragment<VM : BaseViewModel> : DialogFragment() {
    protected lateinit var mViewModel: VM
    private lateinit var layoutView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        layoutView = inflater.inflate(getLayoutResId(), container, false)
        initVM()
        initView()
        initData()
        startObserve()
        return layoutView
    }

    override fun getView(): View? {
        return layoutView
    }

    open fun startObserve() {}
    abstract fun getLayoutResId(): Int
    protected fun backgroundTransparent(): Boolean = false
    abstract fun initView()

    abstract fun initData()

    private fun initVM() {
        providerVMClass().let {
//            ViewModelProvider(this).get(it)
            mViewModel = ViewModelProviders.of(this).get(it)

            lifecycle.addObserver(mViewModel)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        super.onActivityCreated(savedInstanceState)
        fullScreenDialog()
        setBackground()

    }

    //全屏dialog
    private fun fullScreenDialog() {
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialog?.window?.attributes?.windowAnimations = setWindowAnimations()
    }


    //设置背景透明
    private fun setBackground() {
        if (backgroundTransparent()) {
            dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        } else dialog?.window?.setBackgroundDrawable(ColorDrawable(0x00000000))
    }

    abstract fun providerVMClass(): Class<VM>

    override fun onDestroy() {
        this.dismiss()
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }
    override fun show(manager: FragmentManager, tag: String?) {
        try {
            manager.beginTransaction().remove(this).commit()
            super.show(manager, tag)
        } catch (e: Exception) {
            //同一实例使用不同的tag会异常,这里捕获一下
            e.printStackTrace()
        }
    }
    //设置弹出动画
    protected open fun setWindowAnimations(): Int {
        return 0
    }
}