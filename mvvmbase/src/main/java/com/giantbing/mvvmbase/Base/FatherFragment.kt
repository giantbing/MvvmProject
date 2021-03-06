package com.mqq.ssby.pyx.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 * Created by Administrator on 2017/10/19 0019.
 */

abstract class FatherFragment : Fragment() {

    var contentView: View? = null
    open lateinit var mContext: Context


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.mContext = activity!!
        if (contentView == null) {
            contentView = inflater.inflate(layoutID(), container, false)
            onCreateFragment(contentView)
            removeViewGroup()
        }
        return contentView
    }

    override fun getView(): View? {
        return contentView
    }

    private fun removeViewGroup() {
        var viewGroup: ViewGroup? = contentView?.parent as? ViewGroup
        viewGroup?.removeView(contentView)
    }

    protected abstract fun layoutID(): Int

    protected abstract fun onCreateFragment(contentView: View?)
}
