package com.ahgitdevelopment.goosesquizzes.di.module

import com.ahgitdevelopment.goosesquizzes.ui.login.LoginContract
import com.ahgitdevelopment.goosesquizzes.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun getLoginPresenter(): LoginContract.Presenter {
        return LoginPresenter()
    }
}