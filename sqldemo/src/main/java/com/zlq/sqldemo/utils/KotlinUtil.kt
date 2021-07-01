package com.zlq.sqldemo.utils

/**
 *   @Auther:  asad
 *   @Date:  2021/06/30 17:05
 *   @Description:  TODO
 *   @ClassName:  com.zlq.sqldemo.utils
 *   @PackageName:  KotlinUtil
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
fun <T> T.build(block:T.()->Unit):T{
    block()
    return this
}