package Models

import org.litepal.crud.DataSupport

/**
 * Created by Liang Zihong on 2018/4/10.
 */
class District: DataSupport() {

    var city_id:Int=-1
    var district_id=-1
    var name: String=""
    var weather_id: String=""

}