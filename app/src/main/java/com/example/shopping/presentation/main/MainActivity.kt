package com.example.shopping.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.shopping.presentation.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private lateinit var builder: NavOptions.Builder

    override val viewModel by viewModel<MainViewModel>()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun observeData() = viewModel.mainStateLiveData.observe(this){
        when(it){
            is MainState.UnInitialized -> {
                initViews()
                initNavigation()
            }
        }
    }

    private fun initViews() = with(binding){

    }

    private fun initNavigation() = with(binding){

        // Navigation Fragment 생성
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        // Navigation Controller 생성
        val navController = navHostFragment.navController

        // BottomNavigation, Navigation Controller 추가
        bottomNavigation.setupWithNavController(navController)
    }
}