package com.example.shopping.di

import com.example.shopping.data.local.AppPreferenceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val LocalModule = module {
    single { AppPreferenceManager(androidApplication()) }
}