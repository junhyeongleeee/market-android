package com.example.shopping.data.remote.url

object Url {
    const val BASE_URL = "https://bc09-175-209-247-189.jp.ngrok.io"
    const val MOCK_URL = "/v1/"

    const val PUT_CATEGORY = ""
    const val GET_CATEGORY = "/categories"
    const val GET_PRODUCTS_BY_CATEGORY = "/categories/{category_id}/products"

    const val CREATE_USER = "/users/create"
    const val SIGN_IN = "/auth/sign-in"
    const val SIGN_OUT = "/auth/sign-out"
    const val GET_USER_DETAIL = "/users/profile"
    const val GET_HOME = "/home"
    const val GET_PRODUCT_DETAIL = "/products/{product_id}"

    const val ORDERS_CREATE = "/orders/create"
    const val GET_ORDERS = "/orders"

    const val REQUEST_REFUND_CANCEL = "/refunds/{order_id}/create"
}