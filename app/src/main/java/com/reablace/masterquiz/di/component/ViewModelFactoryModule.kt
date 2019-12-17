package com.reablace.masterquiz.di.component

import com.reablace.masterquiz.common.ViewModelFactory
import com.reablace.masterquiz.firebase.auth.FirebaseAuthRepository
import com.reablace.masterquiz.firebase.firestore.FirestoreRepository
import com.reablace.masterquiz.ui.login.LoginFirebaseViewModel
import com.reablace.masterquiz.ui.main.EventListViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactoryModule {
    @Provides
    fun viewModelFactory(
        loginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel>,
        eventListViewModel: Provider<EventListViewModel>
    ): ViewModelFactory {
        return ViewModelFactory(
            loginFirebaseViewModelProvider,
            eventListViewModel)
    }

    @Provides
    fun getLoginFirebaseViewModel(): LoginFirebaseViewModel {
        return LoginFirebaseViewModel(
            firebaseAuthRepository = FirebaseAuthRepository(), firestoreRepository = FirestoreRepository())
    }

    @Provides
    fun getMainViewModel(): EventListViewModel {
        return EventListViewModel(firestoreRepository = FirestoreRepository())
    }
}