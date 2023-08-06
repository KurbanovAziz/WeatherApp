package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ForecastRepository {

    fun getForecast(
        key: String,
        q: String,
        days: Int
    ): Flow<Resource<Weather>>

}