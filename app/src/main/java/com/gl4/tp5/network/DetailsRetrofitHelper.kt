import com.gl4.tp5.network.DetailsApi
import com.gl4.tp5.network.WeatherAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DetailsRetrofitHelper {

    private const val baseUrl ="https://api.openweathermap.org/data/2.5/forecast/"
    /**
     * The Retrofit object with Gson converter.
     */
    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()
    /**
     * A public Api object that exposes the lazy-initialized Retrofit service
     */
    val retrofitService : DetailsApi by lazy { retrofit.create(DetailsApi::class.java) }

}