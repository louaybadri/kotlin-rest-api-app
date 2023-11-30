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
        binding.button2.setOnClickListener {
            model.loadData("london","17db59488cadcad345211c36304a9266")
        }
    }
}