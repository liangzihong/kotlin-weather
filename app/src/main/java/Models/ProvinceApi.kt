package Models

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Liang Zihong on 2018/4/10.
 */
interface ProvinceApi {

    @GET("china")
    fun getProvinceService() : Call<List<Province>>

}