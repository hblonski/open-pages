package com.hb.pages.di

import com.hb.pages.api.google.GoogleBooksAPIClient
import com.hb.pages.util.WebServer
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val mockApiModule = module {
    single {
        Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        OkHttpClient
            .Builder()
            .build()
    }
    single {
        Retrofit
            .Builder()
            .baseUrl(WebServer.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(get()).asLenient())
            .client(get())
            .build()
    }
    single { get<Retrofit>().create(GoogleBooksAPIClient::class.java) }
    single { WebServer.mockWebServer }
}