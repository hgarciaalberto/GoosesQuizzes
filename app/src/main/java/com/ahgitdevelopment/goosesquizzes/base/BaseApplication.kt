package com.ahgitdevelopment.goosesquizzes.base

import androidx.multidex.MultiDexApplication
import com.ahgitdevelopment.goosesquizzes.di.application.ApplicationComponent
import com.ahgitdevelopment.goosesquizzes.di.application.ApplicationModule
import com.ahgitdevelopment.goosesquizzes.di.application.DaggerApplicationComponent
import com.ahgitdevelopment.goosesquizzes.di.component.SharedPrefsModule

open class BaseApplication : MultiDexApplication() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    private val appComponent: ApplicationComponent by lazy {

        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .sharedPrefsModule(SharedPrefsModule())
            .build()
    }

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return appComponent
    }

}
