package com.example.shopping.model.type

enum class OrderType(val type: String) {
    Pending("Pending"),
    Delivering("Delivering"),
    Delivered("Delivered"),
    Cancelled("Cancelled"),
    Refunded("Refunded"),
    Cancelling("Cancelling"),
    Refunding("Refunding")
}