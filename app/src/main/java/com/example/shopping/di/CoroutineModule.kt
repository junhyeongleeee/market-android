package com.example.shopping.di

import android.content.res.loader.ResourcesProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val CoroutineModule = module {

    single { Dispatchers.IO }
    single { Dispatchers.Main }
}