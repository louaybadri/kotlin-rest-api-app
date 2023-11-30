package com.gl4.tp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.gl4.tp5.databinding.ActivityMainBinding
import com.gl4.tp5.model.WeatherApiResponse
import com.gl4.tp5.viewmodel.WeatherViewModel




class MainActivity : AppCompatActivity() {
    private val model: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //activatinf the binding
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        lateinit var weather: WeatherApiResponse
//        //calling the api
//        val call = RetrofitHelper.retrofitService.getWeatherTN()
//        //executing the call
//        call.enqueue(object : retrofit2.Callback<WeatherApiResponse> {
//            override fun onResponse(
//                call: retrofit2.Call<WeatherApiResponse>,
//                response: retrofit2.Response<WeatherApiResponse>
//            ) {
//                if (response.isSuccessful) {
//                    println("response is successful")
//                    weather = response.body()!!
//                    binding.temperature.text = "temp : "+ weather.main.temp.toString()
//                        binding.description.text = "desc: "+ weather.weather[0].description
//                    binding.humidity.text ="humidity: "+  weather.main.humidity.toString()
//                    binding.pressure.text ="pressure : "+  weather.main.pressure.toString()
//
//                }else{
//                    println("response is not successful")
//                    binding.textView.text = response.code().toString()
//
//                }
//            }
//
//            override fun onFailure(call: retrofit2.Call<WeatherApiResponse>, t: Throwable) {
//                binding.textView.text = t.message
//            }
//        })

        val weatherObserver = Observer<WeatherApiResponse> { weather ->
            binding.temperature.text = "temp : "+ weather.main.temp.toString()
            binding.description.text = "desc: "+ weather.weather[0].description
            binding.humidity.text ="humidity: "+  weather.main.humidity.toString()
            binding.pressure.text ="pressure : "+  weather.main.pressure.toString()
        }
        model.weatherData.observe(this, weatherObserver)

        binding.button.setOnClickListener {
            model.loadDataTN()
        }
    }
}