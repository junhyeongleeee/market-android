package com.example.shopping.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopping.presentation.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun observeData() {
    }
}