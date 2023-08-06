package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.model.WeatherModel
import com.example.weatherapp.domain.model.Weather


fun WeatherModel.toForecast() = Weather(
     current,forecast, location
)


