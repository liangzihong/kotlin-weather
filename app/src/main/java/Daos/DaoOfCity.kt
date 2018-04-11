package Daos

import Models.City
import org.litepal.crud.DataSupport

/**
 * Created by Liang Zihong on 2018/4/10.
 */
object DaoOfCity {

    fun addCity(city: City){
        if(city!=null)
            city.save()
    }

    fun getAllCityByProvinceid( provinceid : Int) : List<City>{

        var arr : List<City>
        arr= DataSupport.where("province_id = ?", provinceid.toString()).find(City::class.java)
        return arr
    }

    fun getCityByCityName( cityName : String) : City{
        return DataSupport.where("name = ?",cityName).findFirst(City::class.java)
    }
}



















