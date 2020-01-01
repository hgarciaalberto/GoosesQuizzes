package com.reablace.masterquiz.common

import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.reablace.masterquiz.models.QuizEvent
import java.text.SimpleDateFormat
import java.util.*

//region EditText
@BindingAdapter("errorText")
fun setErrorMessage(view: EditText, errorMessage: Int?) =
    if (errorMessage != null) {
        view.error = view.context.getString(errorMessage)
    } else {
        view.error = null
    }
//endregion


//region TextView
@BindingAdapter("googleAddress")
fun setGoogleAddress(view: TextView, event: QuizEvent?) {
    view.text = event?.getAddressFromGoogle(view.context) ?: ""
}

@BindingAdapter("setFormatDate")
fun setFormatDate(view: TextView, date: Date?) {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    view.text = if (date != null) simpleDateFormat.format(date) else ""
}

@BindingAdapter("setFormatTime")
fun setFormatTime(view: TextView, time: Date?) {
    val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    view.text = if (time != null) simpleDateFormat.format(time) else ""
}
//endregion