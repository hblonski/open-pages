package com.hb.pages.di

import com.hb.pages.usecases.book.SearchBookUseCase
import com.hb.pages.usecases.book.SearchBookUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    single<SearchBookUseCase> { SearchBookUseCaseImpl(get()) }
}