package com.alyjak.reposearch.util

import android.view.View
import android.widget.Spinner
import androidx.databinding.BindingAdapter

@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}

@BindingAdapter("showIfNotNull")
fun showIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.VISIBLE else View.GONE
}

@BindingAdapter("goneIfFalse")
fun goneIfFalse(view: View, it: Boolean?) {
    view.visibility = if (it != null && it) View.VISIBLE else View.GONE
}