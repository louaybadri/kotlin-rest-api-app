package com.gl4.tp5

import android.R
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gl4.tp5.databinding.ActivityMainBinding
import com.gl4.tp5.model.WeatherData
import com.gl4.tp5.viewmodel.WeatherViewModel
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private val weatherModel: WeatherViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //activatinf the binding
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weatherObserver = Observer<WeatherData> { weather ->
            println(weather)
            binding.date.text = weather.date
            binding.temperature.text = weather.temp.toString()
            binding.description.text = weather.description
            binding.humidity.text =weather.humidity.toString()
            binding.pressure.text =weather.pressure.toString()
            try {
                val ims = assets.open(weather.image+"@2x.png")
                val d = Drawable.createFromStream(ims, null)
                binding.mImage.setImageDrawable(d)
            } catch (ex: IOException) {
                println(ex)
                return@Observer
            }
        }
        weatherModel.weatherData.observe(this, weatherObserver)

        binding.button.setOnClickListener {
            weatherModel.loadDataTN()
        }
        val fields = listOf("London", "Paris", "New York", "Tunis")

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, fields)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                weatherModel.loadData(fields[position],"17db59488cadcad345211c36304a9266")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { // Another interface callback
            }
        }
        binding.detailsButton.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("location", binding.spinner.selectedItem.toString())
            intent.putExtra("appId", "17db59488cadcad345211c36304a9266")
            startActivity(intent)
        }
    }
}