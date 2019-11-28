package com.reablace.masterquiz.base

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.reablace.masterquiz.di.component.ControllerModule
import com.reablace.masterquiz.di.component.ControllerSubcomponent

open class BaseFragment : Fragment() {

    @UiThread
    fun getControllerComponent(): ControllerSubcomponent {
        return (activity?.application as BaseApplication)
            .getApplicationComponent()
            .getControllerSubcomponent(ControllerModule(activity!!))
    }
}