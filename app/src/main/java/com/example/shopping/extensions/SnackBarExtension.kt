package com.example.shopping.extensions

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun snackbar(view: View, msg: String) = Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()