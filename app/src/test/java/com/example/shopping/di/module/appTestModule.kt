package com.example.shopping.di.module

import com.example.shopping.data.repository.CategoryRepository
import com.example.shopping.di.provide.buildMockOkHttpClient
import com.example.shopping.di.provide.provideGsonConvertFactory
import com.example.shopping.di.provide.provideMockApiService
import com.example.shopping.domain.repository.CategoryRepositoryImpl
import com.example.shopping.presentation.category.CategoryViewModel
import com.example.shopping.presentation.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appTestModule = module {


    // ViewModel
    viewModel { MainViewModel() }
    viewModel { CategoryViewModel(get()) }

    // Remote
    single { provideMockApiService(get()) }

    single { provideGsonConvertFactory() }
//    single { buildOkHttpClient() }
    single { buildMockOkHttpClient() }

    // Repository

    single<CategoryRepositoryImpl> { CategoryRepository(get(), get()) }

}