package com.fahmi.testapp.base.apiclient

import com.fahmi.testapp.base.state.ApiResponse
import retrofit2.Response

interface ApiClient {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): ApiResponse<Exception, T>
}