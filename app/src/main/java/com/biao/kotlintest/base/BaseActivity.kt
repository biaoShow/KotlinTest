package com.biao.kotlintest.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.ViewBinding
import com.biao.kotlintest.R
import java.lang.Exception
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {
    val TAG = this.javaClass.simpleName
    lateinit var viewBinding: VB
    lateinit var viewModel: VM
    private var loadDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            viewBinding = getVBClass().getMethod("inflate", LayoutInflater::class.java)
                .invoke(null, layoutInflater) as VB
        } catch (e: Exception) {
            e.printStackTrace()
        }
        setContentView(viewBinding.root)

        viewModel = ViewModelProviders.of(this).get(getVMClass())

        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()

    private fun getVBClass(): Class<VB> {
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<VB>
        return clazz
    }

    private fun getVMClass(): Class<VM> {
        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[1] as Class<VM>
        return clazz
    }

    fun showLoad() {
        if (loadDialog == null) {
            createLoad()
        }
        loadDialog?.show()
    }

    fun hideLoad() {
        if (loadDialog != null && loadDialog!!.isShowing)
            loadDialog?.dismiss()
    }

    private fun createLoad() {
        loadDialog = Dialog(this)
        loadDialog?.setContentView(R.layout.loading)
        loadDialog?.setCancelable(false)
    }
}