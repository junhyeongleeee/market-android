package com.example.shopping.di.module

import com.example.shopping.di.provide.*
import org.koin.dsl.module

val RemoteMockModule = module{

    single { provideMockApiService(get()) }

    single { provideGsonConvertFactory() }
//    single { buildOkHttpClient() }
    single { buildMockOkHttpClient() }
}

