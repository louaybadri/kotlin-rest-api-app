package com.gl4.tp5.repository

import com.gl4.tp5.model.WeatherApiResponse
import com.gl4.tp5.network.WeatherAPI
import retrofit2.Call

class WeatherRepo : WeatherAPI {
    override fun getWeatherTN(): Call<WeatherApiResponse> {
        return RetrofitHelper.retrofitService.getWeatherTN()
    }
    override fun getWeather(location:String): Call<WeatherApiResponse> {
        return RetrofitHelper.retrofitService.getWeather(location)
    }

}