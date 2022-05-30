package com.example.shopping.di.module

import com.example.shopping.data.repository.category.CategoryRepository
import com.example.shopping.di.buildMockOkHttpClient
import com.example.shopping.di.provideGsonConvertFactory
import com.example.shopping.di.provideMockApiService
import com.example.shopping.domain.repository.category.CategoryRepositoryImpl
import com.example.shopping.presentation.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appTestModule = module {


    // ViewModel
    viewModel { MainViewModel() }

    // Remote
    single { provideMockApiService(get()) }

    single { provideGsonConvertFactory() }
//    single { buildOkHttpClient() }
    single { buildMockOkHttpClient() }

    // Repository

    single<CategoryRepositoryImpl> { CategoryRepository(get(), get()) }

}