package com.biao.kotlintest.network

import com.biao.kotlintest.bean.NewsRespBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NewsApiServer {
    //请求新闻list
    @FormUrlEncoded
    @POST("/toutiao/index")
    suspend fun getNewsData(
        @Field("key") key: String,
        @Field("type") type: String,
        @Field("page") page: Int
    ): NewsRespBean
}