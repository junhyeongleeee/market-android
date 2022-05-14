package com.example.shopping.di

import com.example.shopping.presentation.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { MainViewModel() }
}