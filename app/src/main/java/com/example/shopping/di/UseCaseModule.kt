package com.example.shopping.di

import com.example.shopping.data.repository.CategoryRepository
import com.example.shopping.domain.repository.CategoryRepositoryImpl
import com.example.shopping.domain.usecases.GetAllCategoriesUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    factory { GetAllCategoriesUseCase(get()) }
}