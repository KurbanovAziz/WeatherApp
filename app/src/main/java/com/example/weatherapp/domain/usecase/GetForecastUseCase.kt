package com.example.weatherapp.domain.usecase

import com.example.weatherapp.domain.repository.ForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) {
    fun getForecast(
        key: String,
        q: String,
        days: Int
    ) = forecastRepository.getForecast(key, q, days)
}