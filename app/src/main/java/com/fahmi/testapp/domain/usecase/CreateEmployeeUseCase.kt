package com.fahmi.testapp.domain.usecase

import com.fahmi.testapp.base.state.Either
import com.fahmi.testapp.domain.model.EmployeeRequest
import com.fahmi.testapp.domain.repository.Repository
import javax.inject.Inject

class CreateEmployeeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(request: EmployeeRequest): String {
        return when (val result = repository.createEmployee(request)) {
            is Either.Success -> result.data
            is Either.Failure -> result.cause.message.orEmpty()
        }
    }
}