package com.ahgitdevelopment.goosesquizzes.di.common

import androidx.multidex.MultiDexApplication
import com.ahgitdevelopment.goosesquizzes.di.application.ApplicationComponent
import com.ahgitdevelopment.goosesquizzes.di.application.DaggerApplicationComponent

open class BaseApplication : MultiDexApplication() {
    // Instance of the AppComponent that will be used by all the Activities in the project
    private var appComponent: ApplicationComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return appComponent
    }
}
