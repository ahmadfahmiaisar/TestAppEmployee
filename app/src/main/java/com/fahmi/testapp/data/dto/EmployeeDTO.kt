package com.fahmi.testapp.data.dto

import com.squareup.moshi.Json

data class EmployeeDTO(
    @field:Json(name = "data")
    val `data`: List<Data>? = null
) {
    data class Data(
        @field:Json(name = "employee_age")
        val employeeAge: Int? = null,

        @field:Json(name = "employee_name")
        val employeeName: String? = null,

        @field:Json(name = "employee_salary")
        val employeeSalary: Int? = null,

        @field:Json(name = "id")
        val id: String? = null,

        @field:Json(name = "profile_image")
        val profileImage: String? = null
    )
}