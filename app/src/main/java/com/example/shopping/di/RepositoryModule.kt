package com.example.shopping.di

import com.example.shopping.data.repository.category.CategoryRepository
import com.example.shopping.data.repository.user.UserRepository
import com.example.shopping.domain.repository.category.CategoryRepositoryImpl
import com.example.shopping.domain.repository.user.UserRepositoryImpl
import org.koin.dsl.module

val RepositoryModule = module {

    single<CategoryRepositoryImpl> { CategoryRepository(get(), get()) }
    single<UserRepositoryImpl> { UserRepository(get(), get()) }
}