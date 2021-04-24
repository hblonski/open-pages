package com.hb.pages.di

import com.hb.pages.BuildConfig
import com.hb.pages.api.google.GoogleBooksAPIClient
import com.hb.pages.api.interceptor.getSimpleLogging
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val apiModule = module {
    single<Moshi> {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        val httpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            // Critical part, LogClient must be last one if you have more interceptors
            httpClientBuilder.addInterceptor(HttpLoggingInterceptor().getSimpleLogging())

        }

        httpClientBuilder.build()

    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.googleBooksApiUrl)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()).asLenient())
            .build()
    }

    single<GoogleBooksAPIClient> { get<Retrofit>().create(GoogleBooksAPIClient::class.java) }
}