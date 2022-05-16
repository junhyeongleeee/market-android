package com.example.shopping.di

import com.example.shopping.data.repository.CategoryRepository
import com.example.shopping.domain.repository.CategoryRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {

    single<CategoryRepositoryImpl> { CategoryRepository(get(), get()) }
}