package com.fahmi.testapp.data.mapper

import com.fahmi.testapp.base.abstraction.Mapper
import com.fahmi.testapp.data.dto.EmployeeDTO
import com.fahmi.testapp.domain.model.Employee
import javax.inject.Inject

class EmployeeMapper @Inject constructor() : Mapper<EmployeeDTO, List<Employee>> {
    override fun map(input: EmployeeDTO): List<Employee> {
        return input.data?.map {
            Employee(
                id = it.id.orEmpty(),
                employeeName = it.employeeName.orEmpty(),
                employeeAge = it.employeeAge ?: 0,
                employeeSalary = it.employeeSalary ?: 0,
                profileImage = it.profileImage.orEmpty()
            )
        } ?: emptyList()
    }
}
