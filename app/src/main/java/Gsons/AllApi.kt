package Gsons

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Liang Zihong on 2018/4/9.
 */
interface AllApi {

    @GET("weather")
    fun getAllService(@Query("cityid") cityid:String ,@Query("key") key:String) : Call<All>
}