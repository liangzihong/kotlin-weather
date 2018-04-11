package Presenters

import Activities.IUpdateView
import Activities.MainActivity
import Gsons.All
import Models.District
import Network.HttpUtil
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Liang Zihong on 2018/4/11.
 */
class UpdatePresenter ( var iview : IUpdateView){



    fun updateData( district: District){
        var weather_id = district.weather_id
        var call = HttpUtil.getWeatherCall(weather_id)
        call.enqueue(object : Callback<All>{
            override fun onFailure(call: Call<All>?, t: Throwable?) {
                Toast.makeText(iview as MainActivity,"获取天气信息失败",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<All>?, response: Response<All>?) {
                var all = response!!.body()
                iview.updateUI(all!!)
            }
        })
    }
}