package com.biao.kotlintest.network.base

import com.biao.kotlintest.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.ParameterizedType
import java.util.concurrent.TimeUnit

abstract class BaseRetrofit<T> {
    private val CONNECT_TIME_OUT = 30 * 1000L
    private val DEFAULT_TIME_OUT = 30 * 1000L
    private lateinit var retrofit: Retrofit

//    private val retrofitMap = HashMap<String, Retrofit>()

//    companion object {
//        val instance = SingletonHolder.holder
//    }
//
//    private object SingletonHolder {
//        val holder = RetrofitHelper()
//    }

    protected fun getRetrofit(
        header: Interceptor = headerInterceptor()
    ): T {
        val baseUrl = setBaseUrl()
        retrofit = Retrofit.Builder()
            .client(okHttpClient(header))
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        return retrofit.create(getTClass()) as T
    }

    private fun getTClass(): Class<T> {
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<T>
        return clazz
    }


    protected abstract fun setBaseUrl(): String

    //    abstract fun setApiServerClass(): Class<*>
    abstract fun getApiServer(): T


    private fun okHttpClient(header: Interceptor): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)//错误重连
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(interceptor)
        }
        builder.addInterceptor(header)//头信息
        return builder.build()
    }

    //设置头信息
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