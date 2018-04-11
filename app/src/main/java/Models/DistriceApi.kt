package Models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Liang Zihong on 2018/4/10.
 */
interface DistriceApi {

    @GET("china/{province_id}/{city_id}")
    fun getDistrictService(@Path("province_id") province_id : String, @Path("city_id") city_id : String) : Call<List<District>>

}