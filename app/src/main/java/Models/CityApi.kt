package Models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Liang Zihong on 2018/4/10.
 */
interface CityApi {

    @GET("china/{province_id}")

    fun getCityService(@Path("province_id") province_id : String) : Call<List<City>>
}