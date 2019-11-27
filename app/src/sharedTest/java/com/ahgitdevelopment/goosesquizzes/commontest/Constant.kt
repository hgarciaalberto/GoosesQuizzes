package com.ahgitdevelopment.goosesquizzes.commontest

import com.ahgitdevelopment.goosesquizzes.models.login.LoggedInUserView
import com.ahgitdevelopment.goosesquizzes.models.login.LoginResult

// region Login

const val ERROR_USER: String = "admin@"
const val VALID_USER: String = "admin"
const val VALID_USER_MAIL: String = "admin@admin.com"
const val VALID_PASSWORD: String = "adminadmin"
const val WRONG_PASSWORD: String = "aaaaaaa"
const val EMPTY_STRING: String = ""
const val SORT_PASSWORD: String = "12"

// Login Response
val RESULT_SUCCESS by lazy {
    LoginResult(
        success = LoggedInUserView(displayName = "", displayId = ""),
        error = null
    )
}

val RESULT_FAIL by lazy {
    LoginResult(
        success = null,
        error = 1
    )
}

// endregion