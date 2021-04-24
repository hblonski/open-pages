package com.hb.pages.di

import com.hb.pages.services.api.BookSearchService
import com.hb.pages.services.api.BookSearchServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    single<BookSearchService> { BookSearchServiceImpl(get()) }
}