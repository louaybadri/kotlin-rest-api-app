package com.gl4.tp5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.tp5.classes.WeatherApiResponse
import com.gl4.tp5.classes.WeatherData
import com.gl4.tp5.repository.WeatherRepo
import java.sql.Date


class WeatherViewModel( private val weatherRepo: WeatherRepo = WeatherRepo()
) : ViewModel() {


   val weatherData: MutableLiveData<WeatherData> by lazy {
        MutableLiveData<WeatherData>()
    }




    fun loadDataTN() {
        var data = weatherRepo.getWeatherTN()
        data.enqueue(object : retrofit2.Callback<WeatherApiResponse> {
            override fun onResponse(
                call: retrofit2.Call<WeatherApiResponse>,
                response: retrofit2.Response<WeatherApiResponse>
            ) {
                if (response.isSuccessful) {
                    println("response is successful")
                    var weather = response.body()!!
                    var _data = WeatherData()
                    _data.date = Date(weather.dt.toLong()*1000).toString()
                    _data.image=weather.weather[0].icon
                    _data.humidity = weather.main.humidity
                    _data.pressure = weather.main.pressure
                    _data.description = weather.weather[0].description
                    _data.temp = weather.main.temp
                    weatherData.value = _data
                } else {
                    println("response is not successful")
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: retrofit2.Call<WeatherApiResponse>, t: Throwable) {
                println("response is not successful")

                println(t.message.toString())
            }
        })

    }

    fun loadData(location: String,appId: String) {
        var data = weatherRepo.getWeather(location,appId)
        data.enqueue(object : retrofit2.Callback<WeatherApiResponse> {
            override fun onResponse(
                call: retrofit2.Call<WeatherApiResponse>,
                response: retrofit2.Response<WeatherApiResponse>
            ) {
                if (response.isSuccessful) {
                    println("response is successful")
                    var weather = response.body()!!
                    var _data = WeatherData()

                    _data.date = Date(weather.dt.toLong()*1000).toString()
                    _data.image=weather.weather[0].icon
                    _data.humidity = weather.main.humidity
                    _data.pressure = weather.main.pressure
                    _data.description = weather.weather[0].description
                    _data.temp = weather.main.temp
                    weatherData.value = _data
                } else {
                    println("response is not successful")
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: retrofit2.Call<WeatherApiResponse>, t: Throwable) {
                println("response is not successful")

                println(t.message.toString())
            }
        })

    }

}