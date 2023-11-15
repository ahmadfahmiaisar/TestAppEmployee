package com.fahmi.testapp.data.mapper

import com.fahmi.testapp.base.abstraction.Mapper
import com.fahmi.testapp.data.dto.EmployeeRequestDTO
import com.fahmi.testapp.domain.model.EmployeeRequest
import javax.inject.Inject

class EmployeeRequestMapper @Inject constructor() : Mapper<EmployeeRequest, EmployeeRequestDTO> {
    override fun map(input: EmployeeRequest): EmployeeRequestDTO {
        return EmployeeRequestDTO(
            input.age,
            input.name,
            input.salary
        )
    }
}