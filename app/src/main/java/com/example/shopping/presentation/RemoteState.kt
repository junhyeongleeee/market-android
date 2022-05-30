package com.example.shopping.presentation

import com.example.shopping.presentation.my.MyState

sealed class RemoteState {
    object BAD_INTERNET : RemoteState()
    object PARSE_ERROR : RemoteState()
    object WRONG_CONNECTION : RemoteState()
    object FAIL : RemoteState()
}

