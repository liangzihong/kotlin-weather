package Daos

import Models.District
import org.litepal.crud.DataSupport

/**
 * Created by Liang Zihong on 2018/4/10.
 */

object DaoOfDistrict {

    fun addDistrict( district : District) = district.save()


    fun getAllDistrictByCityId( id: Int) : List<District> {
        return DataSupport.where("city_id = ?",id.toString()).find(District::class.java)
    }


    fun getDistrictByDistictName( district_name: String) : District = DataSupport.where("name=?", district_name).findFirst(District::class.java)


}