package com.fahmi.testapp.domain.repository

import com.fahmi.testapp.base.state.Either
import com.fahmi.testapp.domain.model.Employee
import com.fahmi.testapp.domain.model.EmployeeRequest

interface Repository {
    suspend fun getEmployees(): Either<Exception, List<Employee>>
    suspend fun createEmployee(request: EmployeeRequest): Either<Exception, String>
    suspend fun updateEmployee(employeeId: String, update: EmployeeRequest): Either<Exception, String>
    suspend fun deleteEmployee(employeeId: String): Either<Exception, String>
}