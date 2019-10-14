package com.ahgitdevelopment.goosesquizzes.ui.login


class LoginPresenter : LoginContract.Presenter {
    private lateinit var view: LoginContract.View

    override fun onLoginClick() {
        //Realizar la llamada a retrofit con user pass
    }


    override fun attach(view: LoginContract.View) {
        this.view = view
    }

}
