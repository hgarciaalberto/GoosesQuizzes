package com.ahgitdevelopment.goosesquizzes.di.common

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.ahgitdevelopment.goosesquizzes.di.component.ControllerModule
import com.ahgitdevelopment.goosesquizzes.di.component.ControllerSubcomponent

open class BaseActivity : AppCompatActivity() {
    @UiThread
    fun getControllerComponent(): ControllerSubcomponent {
        return (application as BaseApplication)
            .getApplicationComponent()
            .getControllerSubcomponent(ControllerModule(this))
    }
}