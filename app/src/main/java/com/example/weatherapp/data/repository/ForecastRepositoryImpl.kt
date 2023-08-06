package com.example.weatherapp.data.repository

import com.example.weatherapp.data.base.BaseRepository
import com.example.weatherapp.data.mappers.toForecast
import com.example.weatherapp.data.remote.service.ApiService
import com.example.weatherapp.domain.repository.ForecastRepository
import javax.inject.Inject

class ForecastRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : BaseRepository(), ForecastRepository {

    override fun getForecast(key: String, q: String, days: Int) = doRequest {
        apiService.forecast(key, q, days).toForecast()
    }

}