package com.gl4.tp5.network

import com.gl4.tp5.model.WeatherApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather?q=Tunis&APPID=17db59488cadcad345211c36304a9266")
    fun getWeatherTN(): Call<WeatherApiResponse>

    @GET("weather?APPID=17db59488cadcad345211c36304a9266")
    fun getWeather(@Query("q") q:String): Call<WeatherApiResponse>
}
