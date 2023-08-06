package com.example.weatherapp.domain.model

data class Current(
    val condition: Condition,
    val last_updated: String,
    val temp_c: Double,
)