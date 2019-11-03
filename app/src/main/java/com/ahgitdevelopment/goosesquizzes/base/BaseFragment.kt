package com.ahgitdevelopment.goosesquizzes.base

import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.ahgitdevelopment.goosesquizzes.di.component.ControllerModule
import com.ahgitdevelopment.goosesquizzes.di.component.ControllerSubcomponent

open class BaseFragment : Fragment() {

    @UiThread
    fun getControllerComponent(): ControllerSubcomponent {
        return (activity?.application as BaseApplication)
            .getApplicationComponent()
            .getControllerSubcomponent(ControllerModule(activity!!))
    }
}