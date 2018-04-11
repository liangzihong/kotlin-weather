package Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.liangzihong.kotlin_weather.R

/**
 * Created by Liang Zihong on 2018/4/11.
 */
class forecast_adapter(context: Context, private val resource_id: Int, objects: List<forecast_model>) : ArrayAdapter<forecast_model>(context, resource_id, objects) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val model = getItem(position)
        val view = LayoutInflater.from(context).inflate(resource_id, parent, false)

        val forecast_item_date = view.findViewById(R.id.forecast_item_date) as TextView
        val forecast_item_weatherState = view.findViewById(R.id.forecast_item_weatherState) as TextView
        val forecast_item_max = view.findViewById(R.id.forecast_item_max) as TextView
        val forecast_item_min = view.findViewById(R.id.forecast_item_min) as TextView

        forecast_item_date.text = model!!.date
        forecast_item_weatherState.text = model.state
        forecast_item_max.text = model.max + "℃"
        forecast_item_min.text = model.min + "℃"

        return view
    }


}