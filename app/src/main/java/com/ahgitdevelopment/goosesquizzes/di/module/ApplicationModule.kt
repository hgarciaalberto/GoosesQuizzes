package com.ahgitdevelopment.goosesquizzes.di.module

import com.ahgitdevelopment.goosesquizzes.di.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApplication) {
    @Provides
    @Singleton
    fun provideApplication(): BaseApplication {
        return baseApp
    }
}