package com.ahgitdevelopment.goosesquizzes.di.module

import com.ahgitdevelopment.goosesquizzes.models.login.LoginDataSource
import com.ahgitdevelopment.goosesquizzes.models.login.LoginRepository
import com.ahgitdevelopment.goosesquizzes.viewmodel.LoginViewModel
import com.ahgitdevelopment.goosesquizzes.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelModule {
    @Provides
    fun viewModelFactory(
        loginViewModelProvider: Provider<LoginViewModel>
        // and so on
    ): ViewModelFactory {
        return ViewModelFactory(
            loginViewModelProvider
            // and so on
        )
    }

    @Provides
    fun getloginViewModel(): LoginViewModel {
        return LoginViewModel(loginRepository = LoginRepository(dataSource = LoginDataSource()))
    }

    @Provides
    fun getLoginRepositor(): LoginRepository {
        return LoginRepository(dataSource = LoginDataSource())
    }

    @Provides
    fun getLoginDataSource(): LoginDataSource {
        return LoginDataSource()
    }


}