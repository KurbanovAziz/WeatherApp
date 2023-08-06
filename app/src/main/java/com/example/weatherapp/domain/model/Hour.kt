package com.example.weatherapp.domain.model

data class Hour(
    val condition: Condition,
    val temp_c: Double,
    val time: String
)