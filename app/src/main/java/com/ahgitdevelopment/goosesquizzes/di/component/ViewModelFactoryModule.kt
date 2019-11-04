package com.ahgitdevelopment.goosesquizzes.di.component

import com.ahgitdevelopment.goosesquizzes.firebase.auth.FirebaseAuthRespository
import com.ahgitdevelopment.goosesquizzes.viewmodel.LoginFirebaseViewModel
import com.ahgitdevelopment.goosesquizzes.viewmodel.ViewModelFactory
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
        return LoginFirebaseViewModel(firebaseAuthRepository = FirebaseAuthRespository())
    }
}