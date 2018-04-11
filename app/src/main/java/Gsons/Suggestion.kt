package Gsons

/**
 * Created by Liang Zihong on 2018/4/9.
 */
class Suggestion {

    var comf :Comf?=null
    var sport: Sport?=null
    var cw:Cw?=null


    class Sport(var brf:String?=null, var txt:String?=null){}

    class Comf(var brf:String?=null, var txt:String?=null){}

    class Cw(var brf:String?=null, var txt:String?=null){}

}