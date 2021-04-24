package com.hb.pages.di

import com.hb.pages.api.google.mapper.GoogleBooksVolumeMapper
import org.koin.dsl.module

val mapperModule = module { 
    single { GoogleBooksVolumeMapper() }
}