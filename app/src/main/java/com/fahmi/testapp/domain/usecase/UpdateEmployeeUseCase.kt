package com.fahmi.testapp.domain.usecase

import com.fahmi.testapp.base.state.Either
import com.fahmi.testapp.domain.model.EmployeeRequest
import com.fahmi.testapp.domain.repository.Repository
import javax.inject.Inject

class UpdateEmployeeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(employeeId: String, update: EmployeeRequest): String {
        return when (val result = repository.updateEmployee(employeeId, update)) {
            is Either.Success -> result.data
            is Either.Failure -> result.cause.message.orEmpty()
        }
    }
}