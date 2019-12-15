package com.reablace.masterquiz.di.component

import com.reablace.masterquiz.common.ViewModelFactory
import com.reablace.masterquiz.firebase.auth.FirebaseAuthRepository
import com.reablace.masterquiz.firebase.firestore.FirestoreRepository
import com.reablace.masterquiz.ui.login.LoginFirebaseViewModel
import com.reablace.masterquiz.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactoryModule {
    @Provides
    fun viewModelFactory(
        loginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel>,
        mainViewModel: Provider<MainViewModel>
    ): ViewModelFactory {
        return ViewModelFactory(
            loginFirebaseViewModelProvider,
            mainViewModel)
    }

    @Provides
    fun getLoginFirebaseViewModel(): LoginFirebaseViewModel {
        return LoginFirebaseViewModel(firebaseAuthRepository = FirebaseAuthRepository())
    }

    @Provides
    fun getMainViewModel(): MainViewModel {
        return MainViewModel(firestoreRepository = FirestoreRepository())
    }
}