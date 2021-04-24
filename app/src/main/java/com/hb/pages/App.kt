package com.hb.pages

import android.app.Application
import com.hb.pages.di.apiModule
import com.hb.pages.di.mapperModule
import com.hb.pages.di.serviceModule
import com.hb.pages.di.useCaseModule
import com.hb.pages.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    open fun startKoin() {
        startKoin {
            androidContext(this@App)
            modules(getModules())
        }
    }

    private fun getModules(): List<Module> {
        return listOf(
            useCaseModule,
            viewModelModule,
            serviceModule,
            apiModule,
            mapperModule,
        )
    }
}