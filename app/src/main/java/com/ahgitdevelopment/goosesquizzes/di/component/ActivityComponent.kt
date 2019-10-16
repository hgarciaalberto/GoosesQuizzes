package com.ahgitdevelopment.goosesquizzes.di.component

import com.ahgitdevelopment.goosesquizzes.di.module.ActivityModule
import com.ahgitdevelopment.goosesquizzes.di.module.ViewModelModule
import com.ahgitdevelopment.goosesquizzes.ui.login.LoginActivity
import dagger.Component

@Component(
    modules = [
        ActivityModule::class,
        ViewModelModule::class]
)
interface ActivityComponent {
    fun inject(loginActivity: LoginActivity)
}