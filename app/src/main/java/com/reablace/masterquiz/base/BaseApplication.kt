package com.reablace.masterquiz.base

import androidx.multidex.MultiDexApplication
import com.reablace.masterquiz.di.application.ApplicationComponent
import com.reablace.masterquiz.di.application.DaggerApplicationComponent

open class BaseApplication : MultiDexApplication() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    private val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.create()
    }

    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return appComponent
    }
}
