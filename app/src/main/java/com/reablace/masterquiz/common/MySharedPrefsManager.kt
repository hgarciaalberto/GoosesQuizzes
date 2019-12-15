package com.reablace.masterquiz.common

import android.content.Context
import android.content.SharedPreferences
import com.reablace.masterquiz.models.login.LoggedInUserView
import javax.inject.Inject

class MySharedPrefsManager @Inject constructor(context: Context, StorageName: String, mode: Int) :
    MySharedPrefsContract {

    private val prefs: SharedPreferences = context.getSharedPreferences(StorageName, mode)

    override fun clearUserSessionData() = prefs.edit().clear().apply()

    override fun addLoginData(user: LoggedInUserView) = prefs.edit()
        .putString(LOGIN_USER_NAME, user.displayName)
        .putString(USER_SESION_AUTH_ID, user.displayId)
        .apply()

    override fun getUserAuthId(): String = prefs.getString(USER_SESION_AUTH_ID, "")!!

    override fun setUserTenancyId(tenancyId: String) = prefs.edit().putString(USER_SESION_TENANT_ID, tenancyId).apply()
    override fun getUserTenancyId(): String = prefs.getString(USER_SESION_TENANT_ID, "")!!


}


interface MySharedPrefsContract {
    fun clearUserSessionData()
    fun addLoginData(user: LoggedInUserView)
    fun getUserAuthId(): String

    fun setUserTenancyId(tenancyId: String)
    fun getUserTenancyId(): String
}
