package com.gl4.tp5.repository

import com.gl4.tp5.classes.DailyWeatherApiResponse
import com.gl4.tp5.network.DetailsApi
import retrofit2.Call

class DetailsRepo : DetailsApi {
    override fun getWeather(q: String, appId: String): Call<DailyWeatherApiResponse> {
        val response =  DetailsRetrofitHelper.retrofitService.getWeather(q,appId)
        println(response.request().url())
        return response
    }
}