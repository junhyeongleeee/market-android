package com.example.shopping.di.module

import com.example.shopping.presentation.category.CategoryViewModel
import com.example.shopping.presentation.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val ViewModelModule = module {

    // ViewModel
    viewModel { MainViewModel() }
    viewModel { CategoryViewModel() }

}