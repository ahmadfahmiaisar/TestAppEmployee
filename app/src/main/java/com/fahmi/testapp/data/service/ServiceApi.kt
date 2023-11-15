package com.fahmi.testapp.data.service

import com.fahmi.testapp.data.dto.EmployeeDTO
import com.fahmi.testapp.data.dto.EmployeeRequestDTO
import com.fahmi.testapp.domain.model.EmployeeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ServiceApi {
    @GET("employees")
    suspend fun getEmployees(): Response<EmployeeDTO>

    @POST("create")
    suspend fun createEmployee(@Body request: EmployeeRequestDTO): Response<String>

    @PUT("update/{employeeId}")
    suspend fun updateEmployee(
        @Path("employeeId") employeeId: String,
        @Body update: EmployeeRequestDTO
    ): Response<String>

    @PUT("delete/{employeeId}")
    suspend fun deleteEmployee(@Path("employeeId") employeeId: String): Response<String>
}
