package com.ebsig.httpsdemo

import java.net.CacheResponse

/**
 *   @Auther:  asad
 *   @Date:  2021/10/08 16:13
 *   @Description:  TODO
 *   @ClassName:  com.ebsig.httpsdemo
 *   @PackageName:  HttpCallbackListener
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e:Exception)
}