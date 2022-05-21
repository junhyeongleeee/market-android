package com.example.shopping.di

import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val RemoteModule = module{

    single { provideApiService(get()) }
    single { provideRetrofit(get(), get()) }
    single { buildOkHttpClient() }
    single { provideGsonConvertFactory() }
}

