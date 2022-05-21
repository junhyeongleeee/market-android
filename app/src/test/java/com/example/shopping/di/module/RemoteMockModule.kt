package com.example.shopping.di.module

import com.example.shopping.di.buildMockOkHttpClient
import com.example.shopping.di.provideGsonConvertFactory
import com.example.shopping.di.provideMockApiService
import org.koin.dsl.module

val RemoteMockModule = module{

    single { provideMockApiService(get()) }

    single { provideGsonConvertFactory() }
//    single { buildOkHttpClient() }
    single { buildMockOkHttpClient() }
}

