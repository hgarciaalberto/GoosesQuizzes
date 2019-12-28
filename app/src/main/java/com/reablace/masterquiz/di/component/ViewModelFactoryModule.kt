package com.reablace.masterquiz.di.component

import com.reablace.masterquiz.common.ViewModelFactory
import com.reablace.masterquiz.firebase.auth.FirebaseAuthRepository
import com.reablace.masterquiz.firebase.firestore.FirestoreRepository
import com.reablace.masterquiz.ui.listevent.MapViewModel
import com.reablace.masterquiz.ui.login.LoginFirebaseViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactoryModule {

    @Provides
    fun viewModelFactory(
        loginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel>,
        mapViewModelProvider: Provider<MapViewModel>
    ): ViewModelFactory {
        return ViewModelFactory(
            loginFirebaseViewModelProvider,
            mapViewModelProvider)
    }

    @Provides
    fun getLoginFirebaseViewModel(): LoginFirebaseViewModel {
        return LoginFirebaseViewModel(
            firebaseAuthRepository = FirebaseAuthRepository(), firestoreRepository = FirestoreRepository())
    }

    @Provides
    fun getMapViewModel(): MapViewModel {
        return MapViewModel(firestoreRepository = FirestoreRepository())
    }

}