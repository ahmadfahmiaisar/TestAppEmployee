package com.fahmi.testapp.data.source

import com.fahmi.testapp.base.apiclient.ApiClient
import com.fahmi.testapp.base.state.ApiResponse
import com.fahmi.testapp.base.state.Either
import com.fahmi.testapp.data.dto.EmployeeDTO
import com.fahmi.testapp.data.dto.EmployeeRequestDTO
import com.fahmi.testapp.data.service.ServiceApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiClient: ApiClient, private val serviceApi: ServiceApi
) {
    suspend fun getEmployees(): Either<Exception, EmployeeDTO> {
        return when (val response = apiClient.safeApiCall { serviceApi.getEmployees() }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun createEmployee(request: EmployeeRequestDTO): Either<Exception, String> {
        return when (val response = apiClient.safeApiCall { serviceApi.createEmployee(request) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun updateEmployee(
        employeeId: String,
        update: EmployeeRequestDTO
    ): Either<Exception, String> {
        return when (val response =
            apiClient.safeApiCall { serviceApi.updateEmployee(employeeId, update) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }

    suspend fun deleteEmployee(employeeId: String): Either<Exception, String> {
        return when (val response =
            apiClient.safeApiCall { serviceApi.deleteEmployee(employeeId) }) {
            is ApiResponse.Success -> Either.Success(response.data)
            is ApiResponse.Failure -> Either.Failure(response.cause)
        }
    }
}