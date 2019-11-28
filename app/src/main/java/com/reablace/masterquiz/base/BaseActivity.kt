package com.reablace.masterquiz.base

import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.reablace.masterquiz.di.component.ControllerModule
import com.reablace.masterquiz.di.component.ControllerSubcomponent

open class BaseActivity : AppCompatActivity() {
    @UiThread
    fun getControllerComponent(): ControllerSubcomponent {
        return (application as BaseApplication)
            .getApplicationComponent()
            .getControllerSubcomponent(ControllerModule(this))
    }
}