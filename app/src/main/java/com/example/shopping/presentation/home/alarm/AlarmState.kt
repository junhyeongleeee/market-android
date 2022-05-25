package com.example.shopping.presentation.home.alarm

import com.example.shopping.presentation.main.MainState

sealed class AlarmState {
    object UnInitialized: AlarmState()
    object Loading: AlarmState()
    sealed class AlarmExistence : AlarmState(){
        object Success: AlarmExistence()
        object Failure: AlarmExistence()
    }
    object Success: AlarmState()
}