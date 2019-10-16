package com.ahgitdevelopment.goosesquizzes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory(
    loginViewModelProvider: Provider<LoginViewModel>,
    loginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel>
) : ViewModelProvider.Factory {

    private var mLoginViewModelProvider: Provider<LoginViewModel> = loginViewModelProvider
    private var mLoginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel> = loginFirebaseViewModelProvider

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> mLoginViewModelProvider.get() as T
            LoginFirebaseViewModel::class.java -> mLoginFirebaseViewModelProvider.get() as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
