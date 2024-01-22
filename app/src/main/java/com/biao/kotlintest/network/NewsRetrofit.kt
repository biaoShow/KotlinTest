package com.biao.kotlintest.network

import com.biao.kotlintest.network.base.BaseRetrofit
import okhttp3.Interceptor

//新闻服务器，每个服务器一个baseRetrofit类
class NewsRetrofit private constructor() : BaseRetrofit<NewsApiServer>() {

    private var newsApiServer: NewsApiServer? = null

    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = NewsRetrofit()
    }


    override fun setBaseUrl(): String {
        return "http://v.juhe.cn/"
    }

    override fun getApiServer(): NewsApiServer {
        if (newsApiServer == null) {
            newsApiServer = getRetrofit()
        }
        return newsApiServer!!
    }


    //设置头信息(需要时候自定义)
    private fun headerInterceptor(): Interceptor {
        return Interceptor { chain ->
            //请求定制(添加请求头)
            val requestBuilder = chain.request().newBuilder().apply {
                //请求定制(添加请求头)
                addHeader("key", "value")
            }
            chain.proceed(requestBuilder.build())
        }
    }


}