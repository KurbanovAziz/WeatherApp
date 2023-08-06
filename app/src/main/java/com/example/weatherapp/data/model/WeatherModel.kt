package com.example.weatherapp.data.model

import com.example.weatherapp.domain.model.Alerts
import com.example.weatherapp.domain.model.Current
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.Location

data class WeatherModel(
    //val alerts: Alerts,
    val current: Current,
    val forecast: Forecast,
    val location: Location
)