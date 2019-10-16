package com.ahgitdevelopment.goosesquizzes.ui.login


class LoginPresenter : LoginContract.Presenter {

    private lateinit var view: LoginContract.View

    override fun attach(view: LoginContract.View) {
        this.view = view
    }
}
