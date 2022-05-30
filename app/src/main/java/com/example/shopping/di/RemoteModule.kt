package com.example.shopping.di

import com.example.shopping.data.local.AppPreferenceManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val RemoteModule = module{

    single { provideApiService(get()) }
    single { provideRetrofit(get(), get()) }
    single { buildOkHttpClient() }
    single { provideGsonConvertFactory() }
}

