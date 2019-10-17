package com.ahgitdevelopment.goosesquizzes.di.module

import com.ahgitdevelopment.goosesquizzes.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule/*(private val activity : Activity)*/ {

//    @Provides
//    fun getContext(): Activity{
//        return activity
//    }

    @Provides
    fun getLoginPresenter(): LoginPresenter {
        return LoginPresenter()
    }
}