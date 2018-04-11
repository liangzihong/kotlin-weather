package Activities

import Gsons.All
import Models.District
import Presenters.UpdatePresenter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.liangzihong.kotlin_weather.R
import org.litepal.LitePal
import Adapters.*
import Presenters.LoadPicPresenter

class MainActivity : AppCompatActivity() , ICloseDrawer , IUpdateView , ILoadPicView{

    //一堆控件
    private var toolbar : Toolbar?=null
    private var drawerLayout: DrawerLayout?=null
    private var backGroundImageView : ImageView?=null
    private var todayweather_temperature : TextView?=null
    private var todayweather_weatherState : TextView?=null
    private var forecast_listView : ListView?=null
    private var aqi_aqiValue : TextView?=null
    private var aqi_PMValue : TextView?=null
    private var aqi_airValue : TextView?=null
    private var suggestion_comfortText : TextView?=null
    private var suggestion_washCarText : TextView?=null
    private var suggestion_sportText : TextView?=null
    private var title_cityName : TextView?=null
    private var title_updateTime : TextView?=null


    private var dataSource : MutableList<forecast_model>?=null
    private var adapter : forecast_adapter?=null

    //更新数据的presenter
    private var updatePresenter = UpdatePresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)


        //获取数据库
        LitePal.getDatabase()
        init()



    }


    //初始化
    fun init(){
        drawerLayout = findViewById(R.id.drawerlayout) as DrawerLayout
        toolbar= findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        backGroundImageView = findViewById(R.id.backGroundImage) as ImageView
        todayweather_temperature = findViewById(R.id.todayweather_temperature) as TextView
        todayweather_weatherState = findViewById(R.id.todayweather_weatherState) as TextView
        forecast_listView =findViewById(R.id.forecast_listView) as ListView
        aqi_aqiValue = findViewById(R.id.aqi_aqiValue) as TextView
        aqi_PMValue = findViewById(R.id.aqi_PMValue) as TextView
        aqi_airValue = findViewById(R.id.aqi_airValue) as TextView
        suggestion_comfortText  = findViewById(R.id.suggestion_comfortText) as TextView
        suggestion_washCarText = findViewById(R.id.suggestion_washCarText) as TextView
        suggestion_sportText = findViewById(R.id.suggestion_sportText) as TextView
        title_cityName = findViewById(R.id.title_cityName) as TextView
        title_updateTime = findViewById(R.id.title_updateTime) as TextView

        dataSource= ArrayList<forecast_model>()
        adapter= forecast_adapter(this,R.layout.forecast_item_layout,dataSource!!)
        forecast_listView!!.adapter= adapter

    }




    // 实现的接口

    //关闭drawer的接口
    override fun closeDrawer() {
        drawerLayout!!.closeDrawer(Gravity.START)
    }

    //更新数据的接口
    override fun startUpdate(district: District) {
        updatePresenter.updateData(district)
        loadPicToView()
    }

    override fun updateUI(all: All) {
        val heWeather = all.heWeathers!![0]
        val basic = heWeather.basic
        val updateTime = heWeather.update
        val now = heWeather.now
        val forecastDayList = heWeather.daily_forecast
        val aqi = heWeather.aqi
        val suggestion = heWeather.suggestion

        title_cityName!!.text = basic!!.parent_city + "-" + basic!!.location
        title_updateTime!!.setText(updateTime!!.loc!!.substring(0, 10))

        todayweather_temperature!!.text = now!!.tmp + "℃"
        todayweather_weatherState!!.text = "风向：" + now!!.wind_dir + "  " +
                "风速：" + now!!.wind_spd + "  " +
                "天气：" + now!!.cond_txt


        dataSource!!.clear()
        for (i in forecastDayList!!.indices) {
            val day = forecastDayList[i]
            val model = forecast_model(day.date!!, day.cond!!.txt_d!!,
                    day.tmp!!.max!!, day.tmp!!.min!!)
            dataSource!!.add(model)
        }
        adapter!!.notifyDataSetChanged()
        //UIHelper.setListViewHeightBasedOnChildren(forecast_listView)



        aqi_aqiValue!!.text = aqi!!.aqi_city!!.aqi
        aqi_PMValue!!.text = aqi!!.aqi_city!!.pm25
        aqi_airValue!!.text = "空气质量：" + aqi!!.aqi_city!!.qlty

        suggestion_comfortText!!.text = "舒适度：" + suggestion!!.comf!!.brf!! + "\n" + suggestion!!.comf!!.txt + "\n"
        suggestion_washCarText!!.text = "洗车建议：" + suggestion!!.cw!!.brf!! + "\n" + suggestion!!.cw!!.txt + "\n"
        suggestion_sportText!!.text = "运动建议：" + suggestion!!.sport!!.brf!! + "\n" + suggestion!!.sport!!.txt + "\n"

    }


    //加载图片的接口
    override fun getImageView(): ImageView = backGroundImageView!!

    override fun loadPicToView() {
        val loadPicPresenter = LoadPicPresenter(this)
        loadPicPresenter.loadPic()
    }


}






















