package com.example.weatherapp.data.remote.service

import com.example.weatherapp.data.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("forecast.json")
    suspend fun forecast(
        @Query("key") key : String,
        @Query("q") q : String,
        @Query("days") days : Int,
    ): WeatherModel
}