package com.ahgitdevelopment.goosesquizzes.di.component

import com.ahgitdevelopment.goosesquizzes.common.ViewModelFactory
import com.ahgitdevelopment.goosesquizzes.firebase.auth.FirebaseAuthRepository
import com.ahgitdevelopment.goosesquizzes.ui.login.LoginFirebaseViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactoryModule {
    @Provides
    fun viewModelFactory(loginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel>): ViewModelFactory {
        return ViewModelFactory(loginFirebaseViewModelProvider)
    }

    @Provides
    fun getloginFirebaseViewModel(): LoginFirebaseViewModel {
        return LoginFirebaseViewModel(firebaseAuthRepository = FirebaseAuthRepository())
    }
}