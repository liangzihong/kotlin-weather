package Fragments

import Activities.ICloseDrawer
import Activities.IUpdateView
import Daos.DaoOfCity
import Daos.DaoOfDistrict
import Daos.DaoOfProvince
import Models.City
import Models.District
import Models.Province
import Network.HttpUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.liangzihong.kotlin_weather.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.HTTP
import javax.security.auth.login.LoginException

/**
 * Created by Liang Zihong on 2018/4/10.
 */
class ChooseLocation: Fragment() , AdapterView.OnItemClickListener{

    var cityButton: Button?=null
    var provinceButton: Button?=null
    var districtButton : Button?=null
    var listView : ListView?=null
    var fragmentView : View?=null
    var adapter : ArrayAdapter<String>?=null
    var arr : MutableList<String>?=null
    var nowState = FragmentState.PROVINCE


    var selectedProvince : Province?=null
    var selectedCity : City?=null
    var selectedDistrict: District?=null


    // 各种view类接口
    var drawerView : ICloseDrawer?=null
    var updateView : IUpdateView?=null


    object FragmentState {
        val PROVINCE=0
        val CITY=1
        val DISTRICT=2
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater?.inflate(R.layout.chooselocation_layout,container,false)
        init()
        return fragmentView
    }


    private fun init() {
        cityButton= fragmentView!!.findViewById(R.id.chooselayout_cityButton) as Button
        provinceButton = fragmentView!!.findViewById(R.id.chooselayout_provinceButton) as Button
        districtButton= fragmentView!!.findViewById(R.id.chooselayout_districtButton) as Button
        listView = fragmentView!!.findViewById(R.id.chooselayout_listView) as ListView
        listView!!.onItemClickListener=this


        drawerView = activity as ICloseDrawer
        updateView = activity as IUpdateView

        arr= ArrayList<String>()
        adapter=ArrayAdapter(context!!,android.R.layout.simple_list_item_1,arr!!)
        listView!!.adapter= adapter
        queryProvince()
    }



    fun queryProvince(){

        arr!!.clear()
        var tmparr = DaoOfProvince.getAllProvince()
        if( tmparr.size==0)
            queryProvinceByServer()
        else
            for( tmpProvince in tmparr) {
                arr!!.add(tmpProvince.name)
            }
        adapter!!.notifyDataSetChanged()
    }



    fun queryProvinceByServer(){
        var call= HttpUtil.getProvinceCall()

        call.enqueue(object : Callback<List<Province>>{
            override fun onResponse(call: Call<List<Province>>?, response: Response<List<Province>>?) {
                var list=response?.body()

                for( tmp in list!!){
                    tmp.save()
                    arr!!.add(tmp.name)
//                    Log.i("fuck","province "+tmp.name)
                }
                adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Province>>?, t: Throwable?) {
                Toast.makeText(activity,"获取省份失败",Toast.LENGTH_LONG).show()
            }
        })
    }


    fun queryCity( province_id : Int) {
        var tmparr= DaoOfCity.getAllCityByProvinceid(province_id)
        arr!!.clear()
        if( tmparr.size==0) {
            queryCityByServer(province_id)
        }
        else {
            for (tmpCity in tmparr) {
                arr!!.add(tmpCity.name)
            }
        }

//        for( tmpCity in arr!!)
//            Log.i("fuck",tmpCity)

        adapter!!.notifyDataSetChanged()
    }

    fun queryCityByServer(province_id: Int ) {
        var call = HttpUtil.getCityCall(province_id.toString())

        call.enqueue(object  : Callback<List<City>>{
            override fun onResponse(call: Call<List<City>>?, response: Response<List<City>>?) {
                var list = response?.body()
                for( tmp in list!!){
                    tmp.province_id=province_id
                    tmp.save()
                    arr!!.add(tmp.name)
                }
//
//                for( tmpCity in arr!!)
//                    Log.i("fuck","by server"+tmpCity)

                adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<City>>?, t: Throwable?) {
                Toast.makeText(activity,"获取城市失败",Toast.LENGTH_LONG).show()
            }
        })
    }


    fun queryDistrict( province_id: Int, city_id: Int){
        arr!!.clear()
        var tmparr= DaoOfDistrict.getAllDistrictByCityId(city_id)
        if(tmparr.size==0)
            queryDistrictByServer(province_id,city_id)
        else
            for( tmpDistrict in tmparr)
                arr!!.add(tmpDistrict.name)
        adapter!!.notifyDataSetChanged()
    }

    fun queryDistrictByServer(province_id: Int , city_id: Int){

        var call = HttpUtil.getDistrictCall(province_id.toString(),city_id.toString())
        call.enqueue(object : Callback<List<District>>{
            override fun onResponse(call: Call<List<District>>?, response: Response<List<District>>?) {
                var list = response?.body()
                for( tmp in list!!){
                    tmp.city_id=city_id
                    tmp.save()
                    arr!!.add(tmp.name)
                }
//
//                for( tmpDistrict in arr!!)
//                    Log.i("fuck","by server"+tmpDistrict)

                adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<District>>?, t: Throwable?) {
                Toast.makeText(activity,"获取城镇失败",Toast.LENGTH_LONG).show()
            }
        })
    }





    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(nowState){
            FragmentState.PROVINCE-> {
                var province_name= arr!!.get(position)
                provinceButton!!.text=province_name
                selectedProvince=DaoOfProvince.getProvinceByProvinceName(province_name)
            }

            FragmentState.CITY->{
                var city_name=arr!!.get(position)
                cityButton!!.text=city_name
                selectedCity=DaoOfCity.getCityByCityName(city_name)
            }

            FragmentState.DISTRICT->{
                var district_name=arr!!.get(position)
                districtButton!!.text=district_name
                selectedDistrict= DaoOfDistrict.getDistrictByDistictName(district_name)
            }

        }
        changeState()
    }


    /**
     * 需要补充， 当选择了 District时要进行什么操作
     */
    fun changeState(){
        when(nowState){

            FragmentState.PROVINCE->{
                queryCity(selectedProvince!!.province_id!!)
                nowState=FragmentState.CITY
            }

            FragmentState.CITY->{
                queryDistrict(selectedCity!!.province_id,selectedCity!!.city_id)
                nowState=FragmentState.DISTRICT
            }

            FragmentState.DISTRICT->{
                drawerView!!.closeDrawer()
                updateView!!.startUpdate(selectedDistrict!!)
                selectedProvince=null
                selectedCity=null
                selectedDistrict=null
                nowState=FragmentState.PROVINCE
                queryProvince()
            }

        }
    }

}















