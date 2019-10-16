package com.ahgitdevelopment.goosesquizzes.ui.login

import com.ahgitdevelopment.goosesquizzes.ui.BaseContract

/**
 * Contract to document what do the Presenter and the View.
 */
class LoginContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>

}
