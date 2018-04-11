package Network

import Activities.MainActivity
import Gsons.All
import Gsons.AllApi
import Models.*
import android.support.annotation.UiThread
import android.widget.ImageView
import com.bumptech.glide.Glide
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Liang Zihong on 2018/4/10.
 */
object HttpUtil {

    val BASEURLOFLOCATION ="http://guolin.tech/api/"
    val BASEURLOFWEATHER="http://guolin.tech/api/"

    val APIKEYOFWEATHER="7235710173ff472c9f9200508645b96b"
    val BASEURLOFPIC="http://guolin.tech/api/bing_pic"


    /**
     * 获取天气的 Call
     */
    fun getWeatherCall( weather_id : String ): Call<All> {

        var retrofit=Retrofit.Builder()
                .baseUrl(BASEURLOFWEATHER)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var api= retrofit.create(AllApi::class.java)
        return api.getAllService(weather_id, APIKEYOFWEATHER)
    }



    /**
     * 获取省份的call
     */
    fun getProvinceCall() : Call<List<Province>> {


        var retrofit=Retrofit.Builder()
                .baseUrl(BASEURLOFLOCATION)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var api = retrofit.create( ProvinceApi::class.java)
        return api.getProvinceService()

    }


    /**
     * 获取城市的call
     */
    fun getCityCall( province_id: String) : Call<List<City>> {
        var retrofit=Retrofit.Builder()
                .baseUrl(BASEURLOFLOCATION)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var api = retrofit.create(CityApi::class.java)
        return api.getCityService(province_id)
    }


    /**
     * 获取地区id
     */
    fun getDistrictCall( province_id: String,city_id : String) : Call<List<District>>{
        var retrofit=Retrofit.Builder()
                .baseUrl(BASEURLOFLOCATION)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var api = retrofit.create(DistriceApi::class.java)
        return api.getDistrictService(province_id,city_id)
    }


}















