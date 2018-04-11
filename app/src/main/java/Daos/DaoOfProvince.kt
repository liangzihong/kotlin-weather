package Daos

import Models.Province
import org.litepal.LitePal
import org.litepal.crud.DataSupport

/**
 * Created by Liang Zihong on 2018/4/10.
 */

object DaoOfProvince {

    fun getAllProvince() : List<Province> = DataSupport.findAll(Province::class.java)

    fun addProvince(province: Province) {
        if(province!=null)
            province.save()
    }

    fun getProvinceByProvinceName( province_name: String) : Province = DataSupport.where("name = ?", province_name).findFirst(Province::class.java)


}
