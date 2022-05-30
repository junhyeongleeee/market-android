package com.example.shopping.di

import com.example.shopping.presentation.category.CategoryViewModel
import com.example.shopping.presentation.category.productsByCategory.ProductsByCategoryViewModel
import com.example.shopping.presentation.home.HomeViewModel
import com.example.shopping.presentation.home.alarm.AlarmViewModel
import com.example.shopping.presentation.main.MainViewModel
import com.example.shopping.presentation.my.MyViewModel
import com.example.shopping.presentation.my.auth.AuthViewModel
import com.example.shopping.presentation.my.auth.navigation.login.LoginViewModel
import com.example.shopping.presentation.my.auth.navigation.register.RegisterViewModel
import com.example.shopping.presentation.my.userDetail.UserDetailViewModel
import com.example.shopping.presentation.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { CategoryViewModel(get()) }
    viewModel { SearchViewModel() }
    viewModel { MyViewModel(get(), get()) }
    viewModel { HomeViewModel() }
    viewModel { (category_id: String) -> ProductsByCategoryViewModel(category_id, get()) }
    viewModel { AlarmViewModel() }
    viewModel { AuthViewModel() }
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { UserDetailViewModel() }
}