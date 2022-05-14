package com.example.shopping.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val CoroutineModule = module {

    single { Dispatchers.IO }
    single { Dispatchers.Main }
}