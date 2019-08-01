package com.giantbing.mvvmproject.model.bean.test

import com.giantbing.mvvmproject.model.bean.Base.BaseBean
import java.io.Serializable


data class LoginBean(
        val data: Data,
        val requesttime: Double,
        val responsetime: Double
) : BaseBean()

data class Data(
        val accid: String,
        val token: String,
        val gender: Int,
        val pushAccid: String,
        val member: Member?,
        val isPerfectInfo: Int,
        val isPerfectInfo2: Int,
        val presentSugar: Int,
        val coin: String,
        val isShield: String,
        val isSelectIntention: String,
        val isActive: String,
        val authCode:String,
        val thresholdShield:String,
        val isNeedCastDoorFee:String,
        val onWalkShieldStatus:String
) : Serializable


data class Member(
        val memberId: Int,
        val nickname: String,
        val birth: String,
        val gender: Int,
        val level: Int,
        val sign: String,
        val avatar: String?,
        val marriage: Int,
        val province: String,
        val city: String,
        val height: String,
        val weight: String,
        val income: String,
        val weixin: String,
        val qq: Any,
        var account: String,
        val helperAccid: HelperAccid?,
        val presentSugar: Int,
        val isPerfectInfo2:Int,
        val isNeedCastDoorFee:String,
        val onWalkShieldStatus:String,
        val isShield:String
) : Serializable

data class HelperAccid(val accid: String, val token: String, val avatar: String, val nickname: String, var save: Boolean = false) : Serializable