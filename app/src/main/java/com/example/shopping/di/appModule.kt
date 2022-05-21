package com.example.shopping.di

import com.example.shopping.data.repository.CategoryRepository
import com.example.shopping.domain.repository.CategoryRepositoryImpl
import com.example.shopping.domain.usecases.GetAllCategoriesUseCase
import com.example.shopping.presentation.category.CategoryViewModel
import com.example.shopping.presentation.home.HomeViewModel
import com.example.shopping.presentation.main.MainViewModel
import com.example.shopping.presentation.my.MyViewModel
import com.example.shopping.presentation.search.SearchViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module{


    viewModel { MainViewModel() }
    viewModel { CategoryViewModel() }
    viewModel { SearchViewModel() }
    viewModel { MyViewModel() }
    viewModel { HomeViewModel() }

    single { Dispatchers.IO }
    single { Dispatchers.Main }

    single<CategoryRepositoryImpl> { CategoryRepository(get(), get()) }

    factory { GetAllCategoriesUseCase(get()) }
}