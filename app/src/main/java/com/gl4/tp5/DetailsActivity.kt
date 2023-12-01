package com.gl4.tp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gl4.tp5.adapters.DetailsAdapter
import com.gl4.tp5.databinding.DetailsBinding
import com.gl4.tp5.classes.WeatherData
import com.gl4.tp5.viewmodel.DailyWeatherViewModel

class DetailsActivity : AppCompatActivity() {


    private lateinit var binding: DetailsBinding
    private val dailyWeatherModel: DailyWeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myAdapter = DetailsAdapter(emptyArray())

        val dailyWeatherObserver = Observer<Array<WeatherData>> { weather ->
            binding.textView.text = "Daily Weather in "+ intent.getStringExtra("location").toString() +" = "+weather.size.toString()+" days weather"
            myAdapter.setData(weather)

            println("i m in the daily: " + myAdapter.itemCount.toString())
        }
        dailyWeatherModel.weatherDailyData.observe(this, dailyWeatherObserver)

        var location:String = intent.getStringExtra("location").toString()
        var appId:String = intent.getStringExtra("appId").toString()
        dailyWeatherModel.loadData(location!!,appId!!)
//        dailyWeatherModel.loadData("tunis","17db59488cadcad345211c36304a9266")

        val recycler = binding.recyclerView
        recycler.apply{
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@DetailsActivity)
        }

    }
}