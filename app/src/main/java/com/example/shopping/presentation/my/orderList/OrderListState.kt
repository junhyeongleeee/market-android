package com.example.shopping.presentation.my.orderList

import com.example.shopping.presentation.main.MainState

sealed class OrderListState {
    object UnInitialized: OrderListState()
    object Loading: OrderListState()
    sealed class AlarmExistence : OrderListState(){
        object Success: AlarmExistence()
        object Failure: AlarmExistence()
    }
    object Success: OrderListState()
    object Failure: OrderListState()
}