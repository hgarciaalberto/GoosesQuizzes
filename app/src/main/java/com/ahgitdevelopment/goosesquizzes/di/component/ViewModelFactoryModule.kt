package com.ahgitdevelopment.goosesquizzes.di.component

import com.ahgitdevelopment.goosesquizzes.firebase.auth.FirebaseAuthRespository
import com.ahgitdevelopment.goosesquizzes.ui.login.LoginDataSource
import com.ahgitdevelopment.goosesquizzes.ui.login.LoginRepository
import com.ahgitdevelopment.goosesquizzes.viewmodel.LoginFirebaseViewModel
import com.ahgitdevelopment.goosesquizzes.viewmodel.LoginViewModel
import com.ahgitdevelopment.goosesquizzes.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactoryModule {
    @Provides
    fun viewModelFactory(
        loginViewModelProvider: Provider<LoginViewModel>,
        loginFirebaseViewModelProvider: Provider<LoginFirebaseViewModel>
    ): ViewModelFactory {
        return ViewModelFactory(
            loginViewModelProvider,
            loginFirebaseViewModelProvider
        )
    }

    @Provides
    fun getloginViewModel(): LoginViewModel {
        return LoginViewModel(loginRepository = LoginRepository(dataSource = LoginDataSource()))
    }

    @Provides
    fun getloginFirebaseViewModel(): LoginFirebaseViewModel {
        return LoginFirebaseViewModel(firebaseAuthRepository = FirebaseAuthRespository())
    }
}