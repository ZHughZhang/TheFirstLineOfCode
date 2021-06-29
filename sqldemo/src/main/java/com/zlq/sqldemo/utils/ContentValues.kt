package com.zlq.sqldemo.utils

import android.content.ContentValues

/**
 *   @Auther:  asad
 *   @Date:  2021/06/28 17:45
 *   @Description:  TODO
 *   @ClassName:  com.zlq.sqldemo.utils
 *   @PackageName:  ContentValues
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
class ContentValues {

    fun cvOf(vararg pairs:Pair<String,Any?>)= ContentValues().apply {
        val  cv = ContentValues()
        for (pair in pairs) {
            val key = pair.first
            val value = pair.second
            when (value) {
                is Int -> cv.put(key,value)
                is Long -> cv.put(key,value)
                is Short -> cv.put(key,value)
                is Double -> cv.put(key,value)
                is Boolean -> cv.put(key,value)
                is String -> cv.put(key,value)
                is Byte -> cv.put(key,value)
                is ByteArray -> cv.put(key,value)
                null -> cv.putNull(key)
            }
        }
    }
}