package com.fahmi.testapp.domain.resource

import com.fahmi.testapp.domain.model.EmployeeUIModel

sealed class EmployeeResource {
    data object Loading : EmployeeResource()
    data class Success(val data: List<EmployeeUIModel>) : EmployeeResource()
    data class Error(val cause: Exception) : EmployeeResource()
}
