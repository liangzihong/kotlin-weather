package Activities

import Gsons.All
import Models.District

/**
 * Created by Liang Zihong on 2018/4/10.
 */
interface IUpdateView {
    fun startUpdate(district: District)
    fun updateUI( all: All)
}