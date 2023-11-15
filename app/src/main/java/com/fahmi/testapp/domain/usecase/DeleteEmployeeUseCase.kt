package com.fahmi.testapp.domain.usecase

import com.fahmi.testapp.base.state.Either
import com.fahmi.testapp.domain.repository.Repository
import javax.inject.Inject

class DeleteEmployeeUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(employeeId: String): String {
        return when (val result = repository.deleteEmployee(employeeId)) {
            is Either.Success -> result.data
            is Either.Failure -> result.cause.message.orEmpty()
        }
    }
}