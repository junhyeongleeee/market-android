package com.example.shopping.model.type

enum class OrderType(val type: String) {
    Pending("Pending"),             // 보류
    Delivering("Delivering"),       // 배송 중
    Delivered("Delivered"),         // 배송 완료
    Cancelled("Cancelled"),         // 취소 됨
    Refunded("Refunded"),           // 환불 됨
    Cancelling("Canceling"),       // 취소 중
    Refunding("Refunding")          // 환불 중
}