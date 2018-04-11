package Presenters

import Activities.ILoadPicView
import Activities.MainActivity
import Network.HttpUtil
import android.app.Activity
import android.util.Log
import com.bumptech.glide.Glide
import com.example.liangzihong.kotlin_weather.R
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Liang Zihong on 2018/4/11.
 */
class LoadPicPresenter (val iview: ILoadPicView): ILoadPicPresenter{

    override fun loadPic() {

        val imageview = iview.getImageView()

        Thread(object : Runnable {
            override fun run() {
                val client = OkHttpClient()
                val request = Request.Builder()
                        .url(HttpUtil.BASEURLOFPIC)
                        .build()

                try {
                    val url = client.newCall(request).execute().body()!!.string()
                    Log.i("fuck",url)

                    (iview as MainActivity).runOnUiThread(object : Runnable{
                        override fun run() {
                            Glide.with( iview as Activity).load(url).into(imageview)
                        }
                    })

                } catch (e: Exception) {
                    e.printStackTrace()
                    (iview as MainActivity).runOnUiThread(object : Runnable{
                        override fun run() {
                            Glide.with( iview as Activity).load(R.mipmap.icon).into(imageview)
                        }
                    })
                }
            }
        }).start()
    }
}