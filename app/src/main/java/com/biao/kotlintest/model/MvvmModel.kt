package com.biao.kotlintest.model

import android.os.SystemClock
import com.biao.kotlintest.Constant
import com.biao.kotlintest.base.BaseModel
import com.biao.kotlintest.bean.NewsRespBean
import com.biao.kotlintest.network.NewsRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MvvmModel : BaseModel() {

    suspend fun getDataTest(sleepTime: Long): String {
        return withContext(Dispatchers.IO) {
            SystemClock.sleep(sleepTime)
            "result data"
        }
    }

    suspend fun getNewsData(page: Int): NewsRespBean {
        return withContext(Dispatchers.IO) {
            NewsRetrofit.instance.getApiServer().getNewsData(Constant.NEWS_KEY, Constant.TOP, page)
        }
    }
}