package com.reablace.masterquiz.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reablace.masterquiz.ui.login.LoginFirebaseViewModel
import com.reablace.masterquiz.ui.main.MainViewModel
import javax.inject.Inject
import javax.inject.Provider

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory @Inject constructor(
    loginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel>,
    mainViewModelProvider: Provider<MainViewModel>) :
    ViewModelProvider.Factory {

    private var mLoginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel> = loginFirebaseViewModelProvider
    private var mMainViewModelProvider: Provider<MainViewModel> = mainViewModelProvider

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginFirebaseViewModel::class.java -> mLoginFirebaseViewModelProvider.get() as T
            MainViewModel::class.java -> mMainViewModelProvider.get() as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
