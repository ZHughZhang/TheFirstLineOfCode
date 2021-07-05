package com.zlq.playaudiotest

/**
 *   @Auther:  asad
 *   @Date:  2021/07/05 15:56
 *   @Description:  TODO
 *   @ClassName:  com.zlq.playaudiotest
 *   @PackageName:  index
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */

fun main(args:Array<String>){
    if ("Hello Kotlion".beaginWith("Hello")){
        print("你好世界")
    }
    val list = listOf(
        "Apple",
        "Orange",
        "Peach",
        "Grape",
        "Banana"
    )

    if (list.has("Peach")) {
        print("你想吃屁")
    }else{
        print("你在想 peach")
    }

}



infix fun String.beaginWith(prefix:String) = startsWith(prefix)

infix fun <T> Collection<T>.has(element: T) = contains(element)

