package com.giantbing.toollib.permission

import android.Manifest
import androidx.fragment.app.FragmentActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable

object Permission {

    fun getCamera(activity: FragmentActivity): Observable<Boolean> {

        return RxPermissions(activity).request(Manifest.permission.CAMERA)
    }

}