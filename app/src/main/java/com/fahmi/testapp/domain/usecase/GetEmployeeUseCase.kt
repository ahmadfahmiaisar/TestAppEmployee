package com.fahmi.testapp.domain.usecase

import com.fahmi.testapp.base.dispatcher.DispatcherProvider
import com.fahmi.testapp.base.state.Either
import com.fahmi.testapp.domain.model.Employee
import com.fahmi.testapp.domain.model.EmployeeUIModel
import com.fahmi.testapp.domain.repository.Repository
import com.fahmi.testapp.domain.resource.EmployeeResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetEmployeeUseCase @Inject constructor(
    private val repository: Repository,
    private val dispatcher: DispatcherProvider
) {
    suspend operator fun invoke(): Flow<EmployeeResource> {
        return flow {
            emit(EmployeeResource.Loading)
            when (val result = repository.getEmployees()) {
                is Either.Success -> emit(EmployeeResource.Success(result.data.map { it.mapToUiModel() }))
                is Either.Failure -> emit(EmployeeResource.Error(result.cause))
            }
        }.flowOn(dispatcher.io)
    }

    private fun Employee.mapToUiModel(): EmployeeUIModel {
        return EmployeeUIModel(
            id,
            "name: $employeeName",
            "age: $employeeAge",
            "salary: $employeeSalary",
            profileImage,
            isProfileImageExist = profileImage.isNotEmpty()
        )
    }
}