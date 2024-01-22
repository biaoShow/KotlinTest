package com.biao.kotlintest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.biao.kotlintest.base.BaseViewModel
import com.biao.kotlintest.model.MvvmModel
import kotlinx.coroutines.launch

class MvvmViewModel : BaseViewModel<MvvmModel>() {
    var text = MutableLiveData("")

    fun operateTest(loadShow: Boolean) {
        loading.postValue(loadShow)
        viewModelScope.launch {
            val dataTest = model.getDataTest(5 * 1000L)
            loading.postValue(false)
            Log.i(TAG, "log1, ${Thread.currentThread().name}, dataTest = $dataTest")
        }
        Log.i(TAG, "log3, ${Thread.currentThread().name}")
    }

    fun getNewsData(page: Int) {
        viewModelScope.launch {
            loading.postValue(true)
            val newsRespBean = model.getNewsData(page)
            loading.postValue(false)
            text.postValue(newsRespBean.toString())
        }
    }
}