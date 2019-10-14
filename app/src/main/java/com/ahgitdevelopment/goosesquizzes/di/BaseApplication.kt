package com.ahgitdevelopment.goosesquizzes.di

import android.app.Application
import com.ahgitdevelopment.goosesquizzes.di.component.ApplicationComponent
import com.ahgitdevelopment.goosesquizzes.di.component.DaggerApplicationComponent

class BaseApplication : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.create()
        component.inject(this)
    }
}
