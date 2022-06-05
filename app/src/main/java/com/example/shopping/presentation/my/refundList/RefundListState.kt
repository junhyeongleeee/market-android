package com.example.shopping.presentation.my.refundList

sealed class RefundListState {
    object UnInitialized: RefundListState()
    object Loading: RefundListState()
    sealed class AlarmExistence : RefundListState(){
        object Success: AlarmExistence()
        object Failure: AlarmExistence()
    }
    object Success: RefundListState()
    object Failure: RefundListState()
}