package Models

import com.google.gson.annotations.SerializedName
import org.litepal.crud.DataSupport

/**
 * Created by Liang Zihong on 2018/4/10.
 */
class City : DataSupport(){

    var province_id=-1

    @SerializedName("id")
    var city_id=-1
    var name: String=""

}