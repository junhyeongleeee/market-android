package com.example.shopping.di

import com.example.shopping.data.repository.category.CategoryRepository
import com.example.shopping.domain.repository.category.CategoryRepositoryImpl
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

}