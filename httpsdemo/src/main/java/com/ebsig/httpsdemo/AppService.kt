package com.ebsig.httpsdemo

import retrofit2.Call
import retrofit2.http.GET

/**
 *   @Auther:  asad
 *   @Date:  2021/10/08 17:11
 *   @Description:  TODO
 *   @ClassName:  com.ebsig.httpsdemo
 *   @PackageName:  AppService
 *   @Version:  1.0
 *   @ProgectName:  TheFirstLineOfCode
 */
interface AppService {
    @GET("get_data.json")
    fun getAppData(): Call<List<App>>
}