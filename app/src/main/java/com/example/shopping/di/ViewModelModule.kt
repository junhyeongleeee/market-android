package com.example.shopping.di

import com.example.shopping.presentation.category.CategoryViewModel
import com.example.shopping.presentation.home.HomeViewModel
import com.example.shopping.presentation.main.MainViewModel
import com.example.shopping.presentation.my.MyViewModel
import com.example.shopping.presentation.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { CategoryViewModel() }
    viewModel { SearchViewModel() }
    viewModel { MyViewModel() }
    viewModel { HomeViewModel() }
}