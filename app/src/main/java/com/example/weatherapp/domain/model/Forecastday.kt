package com.example.weatherapp.domain.model

data class Forecastday(
    val day: Day,
    val hour : List<Hour>,
    val date : String
)