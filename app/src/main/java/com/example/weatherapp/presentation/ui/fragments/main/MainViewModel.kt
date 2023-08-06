package com.example.weatherapp.presentation.ui.fragments.main

import com.example.weatherapp.BuildConfig.API_KEY
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.usecase.GetForecastUseCase
import com.example.weatherapp.presentation.base.BaseViewModel
import com.example.weatherapp.presentation.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val forecastUseCase: GetForecastUseCase,
) : BaseViewModel() {

    private val _forecastState = MutableStateFlow<UIState<Weather>>(UIState.Empty())
    val forecastFilterState = _forecastState.asStateFlow()

    fun forecast(q: String, days: Int) {
        forecastUseCase.getForecast(API_KEY, q, days).collectData(_forecastState)
    }

}