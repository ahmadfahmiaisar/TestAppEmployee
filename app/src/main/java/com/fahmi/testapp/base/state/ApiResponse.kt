package com.fahmi.testapp.base.state

sealed class ApiResponse<A, B> {
    class Success<A, B>(val data: B) : ApiResponse<A, B>()
    class Failure<A, B>(val cause: A) : ApiResponse<A, B>()
}