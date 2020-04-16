package com.alyjak.reposearch.util

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("showIfNotNull")
fun showIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.VISIBLE else View.GONE
}

@BindingAdapter("goneIfFalse")
fun goneIfFalse(view: View, it: Boolean?) {
    view.visibility = if (it != null && it) View.VISIBLE else View.GONE
}

@BindingAdapter("errorText")
fun setErrorMessage(view: TextInputLayout, showError: Boolean?) {
    if (showError != null && showError) {
        view.error = "Query can't be empty"
    } else {
        view.error = null
    }
}