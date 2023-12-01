package com.gl4.tp5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.tp5.classes.DailyWeatherApiResponse
import com.gl4.tp5.classes.WeatherData
import com.gl4.tp5.repository.DetailsRepo
import java.sql.Date

class DailyWeatherViewModel( private val dailyWeather: DetailsRepo = DetailsRepo()): ViewModel() {

    val weatherDailyData: MutableLiveData<Array<WeatherData>> by lazy {
        MutableLiveData<Array<WeatherData>>()
    }
    fun loadData(location: String,appId: String) {
        var data = dailyWeather.getWeather(location,appId)

        data.enqueue(object : retrofit2.Callback<DailyWeatherApiResponse> {
            override fun onResponse(
                call: retrofit2.Call<DailyWeatherApiResponse>,
                response: retrofit2.Response<DailyWeatherApiResponse>
            ) {
                if (response.isSuccessful) {
                    println("response is successful")
                    var weather = response.body()!!
                    var list = listOf<WeatherData>()
                    for(daily in weather.list){

                        var data = WeatherData()
                        data.date = Date(daily.dt.toLong()*1000).toString()
                        data.image=daily.weather[0].icon
                        data.humidity = daily.humidity
                        data.pressure = daily.pressure
                        data.description = daily.weather[0].description
                        data.temp = daily.temp.day
                        list+=data
                        }
                    for (i in list){
                        println(i.humidity)
                    }

                    weatherDailyData.value = list.toTypedArray()
                    println(weatherDailyData.value!!.size)
                } else {
                    println("response is not successful")
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: retrofit2.Call<DailyWeatherApiResponse>, t: Throwable) {
                println("response is not successful")
                println(t.message.toString())
            }
        })

    }
}