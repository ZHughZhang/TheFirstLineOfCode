package com.zlq.sqldemo.utils

import kotlin.reflect.KProperty


fun <T> later (block:()->T) = Later(block)


/**
 *   @Auther:  asad
 *   @Date:  2021/06/30 17:22
 *   @Description:  TODO
 *   @ClassName:  com.zlq.sqldemo.utils
 *   @PackageName:  Later
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
class Later<T>(val block:() ->T) {
    var value:Any? = null


    operator fun getValue(any: Any?,prop:KProperty<*>):T{
        if (value == null) {
            value = block()
        }
        return value as T
    }
}