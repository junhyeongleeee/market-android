package com.example.shopping.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun snackbar(view: View, msg: String) = Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()

fun toast(context: Context, msg: String) = Toast.makeText(context, msg, Toast.LENGTH_LONG).show()