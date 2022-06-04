package com.example.shopping.di

import com.example.shopping.data.local.AppPreferenceManager
import com.example.shopping.util.provider.DefaultResourcesProvider
import com.example.shopping.util.provider.ResourcesProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val UtilModule = module {
    single<ResourcesProvider> { DefaultResourcesProvider(androidApplication()) }
}