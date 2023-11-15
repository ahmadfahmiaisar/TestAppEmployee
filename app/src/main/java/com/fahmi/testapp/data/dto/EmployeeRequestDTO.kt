package com.fahmi.testapp.data.dto

import com.squareup.moshi.Json

data class EmployeeRequestDTO(
    @field:Json(name = "age")
    val age: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "salary")
    val salary: String
)