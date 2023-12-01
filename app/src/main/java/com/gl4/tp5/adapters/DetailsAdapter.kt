package com.gl4.tp5.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp5.R
import com.gl4.tp5.databinding.ItemBinding
import com.gl4.tp5.classes.WeatherData
import java.io.IOException

class DetailsAdapter(private val weatherData: Array<WeatherData>): RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder>() {
    var _weatherData: Array<WeatherData> = weatherData

    private lateinit var binding: ItemBinding
    fun setData(data: Array<WeatherData>) {
        _weatherData = data
        notifyDataSetChanged()
    }
    class DetailsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var date :TextView = view.findViewById(R.id.date_item)
        var temp:TextView = view.findViewById(R.id.temperature_item)
        var description:TextView = view.findViewById(R.id.description_item)
        var humidity:TextView = view.findViewById(R.id.humidity_item)
        var pressure:TextView = view.findViewById(R.id.pressure_item)
        var image:ImageView = view.findViewById(R.id.mImage_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false) // Replace with your item layout
        return DetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        println("size is : "+_weatherData.size.toString())
        return _weatherData.size
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.date.text = _weatherData[position].date
        holder.temp.text = _weatherData[position].temp.toString()
        holder.description.text = _weatherData[position].description
        holder.humidity.text = _weatherData[position].humidity.toString()
        holder.pressure.text = _weatherData[position].pressure.toString()
        try {

            val context = holder.itemView.context
            val assetManager = context.assets
            val ims = assetManager.open(_weatherData[position].image+"@2x.png")
            val d = Drawable.createFromStream(ims, null)
            holder.image.setImageDrawable(d)
        } catch (ex: IOException) {
            println(ex)
            return
        }

    }
}