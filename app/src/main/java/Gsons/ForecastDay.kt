package Gsons

/**
 * Created by Liang Zihong on 2018/4/9.
 */
class ForecastDay {

    var date:String?=null
    var cond : Cond?=null
    var tmp : Tmp?=null

    class Cond(var txt_d: String?=null){}
    class Tmp(var max : String?=null, var min:String?=null){}
}