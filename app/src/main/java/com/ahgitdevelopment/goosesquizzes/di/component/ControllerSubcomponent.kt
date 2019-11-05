package com.ahgitdevelopment.goosesquizzes.di.component

import com.ahgitdevelopment.goosesquizzes.ui.listevent.EventListFragment
import com.ahgitdevelopment.goosesquizzes.ui.login.LoginActivity
import com.ahgitdevelopment.goosesquizzes.ui.login.LoginPresenter
import com.ahgitdevelopment.goosesquizzes.ui.main.MainActivity
import dagger.Subcomponent


/**
 * It is used to inject services into Activities and Fragments
 */
@Subcomponent(
    modules = [
        ControllerModule::class,
        ViewModelFactoryModule::class
//        LoginModule::class,
//        EventListModule::class
    ]
)
interface ControllerSubcomponent {

    fun inject(loginActivity: LoginActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(eventListFragment: EventListFragment)

    fun inject(loginPresenter: LoginPresenter)

}