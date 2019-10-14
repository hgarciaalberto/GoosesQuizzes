package com.ahgitdevelopment.goosesquizzes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Provider

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory(loginViewModelProvider: Provider<LoginViewModel>) : ViewModelProvider.Factory {

    private var mLoginViewModelProvider: Provider<LoginViewModel> = loginViewModelProvider

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginViewModel::class.java -> mLoginViewModelProvider.get() as T

            // Add new ViewModels

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
