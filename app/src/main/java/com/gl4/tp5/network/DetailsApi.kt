package com.gl4.tp5.network

import com.gl4.tp5.classes.DailyWeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailsApi {

    @GET("daily")
    fun getWeather(@Query("q") q: String, @Query("APPID") appId: String): Call<DailyWeatherApiResponse>
}