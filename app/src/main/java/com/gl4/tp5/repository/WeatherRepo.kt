package com.gl4.tp5.repository

import com.gl4.tp5.model.WeatherApiResponse
import com.gl4.tp5.network.WeatherAPI
import retrofit2.Call

class WeatherRepo : WeatherAPI {
    override fun getWeatherTN(): Call<WeatherApiResponse> {
        val response =  RetrofitHelper.retrofitService.getWeatherTN()
        println(response.request().url())
        return response
    }
    override fun getWeather(location:String,appId:String): Call<WeatherApiResponse> {

        val response =  RetrofitHelper.retrofitService.getWeather(location,appId)
        println(response.request().url())
        return response
    }

}