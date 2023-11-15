package com.fahmi.testapp.data.dto

import com.squareup.moshi.Json

data class DefaultDTO(
    @field:Json(name = "message")
    val message: String,

    @field:Json(name = "status")
    val status: String
)