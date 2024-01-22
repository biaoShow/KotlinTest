package com.biao.kotlintest.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.reflect.ParameterizedType

open class BaseViewModel<M : BaseModel> : ViewModel() {
    val TAG = this.javaClass.simpleName
    lateinit var model: M
    var loading = MutableLiveData(false)//true:show Loading, false:hied Loading

    init {
        model = getVBClass().newInstance()
    }

    private fun getVBClass(): Class<M> {
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<M>
        return clazz
    }

}