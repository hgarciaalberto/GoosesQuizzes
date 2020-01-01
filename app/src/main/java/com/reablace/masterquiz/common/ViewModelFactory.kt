package com.reablace.masterquiz.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reablace.masterquiz.ui.detailevent.EventDetailViewModel
import com.reablace.masterquiz.ui.listevent.MapViewModel
import com.reablace.masterquiz.ui.login.LoginFirebaseViewModel
import javax.inject.Inject
import javax.inject.Provider

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class ViewModelFactory @Inject constructor(
    loginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel>,
    mapViewModelProvider: Provider<MapViewModel>,
    eventDetailViewModelProvider: Provider<EventDetailViewModel>) :
    ViewModelProvider.Factory {

    private var mLoginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel> = loginFirebaseViewModelProvider
    private var mMapViewModelProvider: Provider<MapViewModel> = mapViewModelProvider
    private var mEventDetailViewModelProvider: Provider<EventDetailViewModel> = eventDetailViewModelProvider

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            LoginFirebaseViewModel::class.java -> mLoginFirebaseViewModelProvider.get() as T
            MapViewModel::class.java -> mMapViewModelProvider.get() as T
            EventDetailViewModel::class.java -> mEventDetailViewModelProvider.get() as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
