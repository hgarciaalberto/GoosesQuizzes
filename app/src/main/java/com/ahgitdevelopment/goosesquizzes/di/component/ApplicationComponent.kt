package com.ahgitdevelopment.goosesquizzes.di.component

import com.ahgitdevelopment.goosesquizzes.di.BaseApplication
import com.ahgitdevelopment.goosesquizzes.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(baseApplication: BaseApplication)
}