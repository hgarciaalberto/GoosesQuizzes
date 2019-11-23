package com.ahgitdevelopment.goosesquizzes.viewmodel

import android.widget.EditText
import androidx.databinding.BindingAdapter


//region EditText
@BindingAdapter("errorText")
fun setErrorMessage(view: EditText, errorMessage: Int?) =
        if (errorMessage != null) {
            view.error = view.context.getString(errorMessage)
        } else {
            view.error = null
        }


//endregion