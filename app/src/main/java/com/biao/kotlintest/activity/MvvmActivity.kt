package com.biao.kotlintest.activity

import android.os.Bundle
import com.biao.kotlintest.base.BaseActivity
import com.biao.kotlintest.databinding.ActivityMvvmBinding
import com.biao.kotlintest.viewmodel.MvvmViewModel

class MvvmActivity : BaseActivity<ActivityMvvmBinding, MvvmViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        viewBinding.btnTest.setOnClickListener {
            viewModel.operateTest(true)
        }

        viewBinding.btnGetNews.setOnClickListener {
            viewModel.getNewsData(1)
        }
    }

    override fun initData() {
        viewModel.loading.observe(this) {
            if (it) showLoad()
            else hideLoad()
        }

        viewModel.text.observe(this) {
            viewBinding.tvText.text = it
        }
    }
}