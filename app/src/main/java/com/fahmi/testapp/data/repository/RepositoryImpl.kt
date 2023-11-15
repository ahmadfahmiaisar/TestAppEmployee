package com.fahmi.testapp.data.repository

import com.fahmi.testapp.base.state.Either
import com.fahmi.testapp.data.mapper.EmployeeMapper
import com.fahmi.testapp.data.mapper.EmployeeRequestMapper
import com.fahmi.testapp.data.source.RemoteDataSource
import com.fahmi.testapp.domain.model.Employee
import com.fahmi.testapp.domain.model.EmployeeRequest
import com.fahmi.testapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val employeeMapper: EmployeeMapper,
    private val requestMapper: EmployeeRequestMapper
) : Repository {
    override suspend fun getEmployees(): Either<Exception, List<Employee>> {
        return when (val result = remoteDataSource.getEmployees()) {
            is Either.Failure -> Either.Failure(result.cause)
            is Either.Success -> Either.Success(employeeMapper.map(result.data))
        }
    }

    override suspend fun createEmployee(request: EmployeeRequest): Either<Exception, String> {
        return when (val result = remoteDataSource.createEmployee(requestMapper.map(request))) {
            is Either.Failure -> Either.Failure(result.cause)
            is Either.Success -> Either.Success(result.data)
        }
    }

    override suspend fun updateEmployee(
        employeeId: String,
        update: EmployeeRequest
    ): Either<Exception, String> {
        return when (val result =
            remoteDataSource.updateEmployee(employeeId, requestMapper.map(update))) {
            is Either.Failure -> Either.Failure(result.cause)
            is Either.Success -> Either.Success(result.data)
        }
    }

    override suspend fun deleteEmployee(employeeId: String): Either<Exception, String> {
        return when (val result = remoteDataSource.deleteEmployee(employeeId)) {
            is Either.Failure -> Either.Failure(result.cause)
            is Either.Success -> Either.Success(result.data)
        }
    }
}
