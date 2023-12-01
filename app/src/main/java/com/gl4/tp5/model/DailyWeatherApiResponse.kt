package com.gl4.tp5.model

data class DailyWeatherApiResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DailyDetail>,
    val message: Double
)