package Gsons

import com.google.gson.annotations.SerializedName

/**
 * Created by Liang Zihong on 2018/4/9.
 */
class All {

    @SerializedName("HeWeather")
    var heWeathers : List<HeWeather>?=null


    class HeWeather{
        var basic : Basic?=null
        var update : Update?=null
        var now : Now?=null
        var daily_forecast : List<ForecastDay>?=null
        var aqi :Aqi?=null
        var suggestion : Suggestion?=null
    }



}