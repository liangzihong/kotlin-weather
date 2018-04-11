package Models

import com.google.gson.annotations.SerializedName
import org.litepal.crud.DataSupport

/**
 * Created by Liang Zihong on 2018/4/10.
 */
class Province : DataSupport() {
    @SerializedName("id")
    var province_id=-1
    var name=""
}