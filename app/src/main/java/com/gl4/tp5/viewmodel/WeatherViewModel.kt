package com.gl4.tp5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gl4.tp5.model.WeatherApiResponse
import com.gl4.tp5.repository.WeatherRepo

class WeatherData {
    var temp: Double = 0.0
    var description: String = ""
    var humidity: Int = 0
    var pressure: Int = 0
//
//    constructor(temp: Double, description: String, humidity: Int, pressure: Int) {
//        this.temp = temp
//        this.description = description
//        this.humidity = humidity
//        this.pressure = pressure
//    }
}


class WeatherViewModel() : ViewModel() {


    private val weatherRepo: WeatherRepo = WeatherRepo()
    val weatherData: MutableLiveData<WeatherApiResponse> by lazy {
        MutableLiveData<WeatherApiResponse>()
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
                    _data.humidity = weather.main.humidity
                    _data.pressure = weather.main.pressure
                    _data.description = weather.weather[0].description
                    _data.temp = weather.main.temp
                    weatherData.value = weather
                } else {
                    println("response is not successful")
                }
            }

            override fun onFailure(call: retrofit2.Call<WeatherApiResponse>, t: Throwable) {
                println("response is not successful")
            }
        })

    }

    fun loadData(location: String) {
        var data = weatherRepo.getWeather(location)
        data.enqueue(object : retrofit2.Callback<WeatherApiResponse> {
            override fun onResponse(
                call: retrofit2.Call<WeatherApiResponse>,
                response: retrofit2.Response<WeatherApiResponse>
            ) {
                if (response.isSuccessful) {
                    println("response is successful")
                    var weather = response.body()!!
                    var _data = WeatherData()
                    _data.humidity = weather.main.humidity
                    _data.pressure = weather.main.pressure
                    _data.description = weather.weather[0].description
                    _data.temp = weather.main.temp
                    weatherData.value = weather
                } else {
                    println("response is not successful")
                }
            }

            override fun onFailure(call: retrofit2.Call<WeatherApiResponse>, t: Throwable) {
                println("response is not successful")
            }
        })

    }

}