package Gsons

import com.google.gson.annotations.SerializedName

/**
 * Created by Liang Zihong on 2018/4/9.
 */
class Aqi {

    @SerializedName("city")
    var aqi_city: Aqi_city?=null

    class Aqi_city {
        var aqi :String?=null
        var pm25 :String?=null
        var qlty :String?=null
    }

}