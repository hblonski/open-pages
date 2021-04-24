package com.hb.pages

import com.hb.pages.di.mapperModule
import com.hb.pages.di.mockApiModule
import com.hb.pages.di.serviceModule
import com.hb.pages.di.useCaseModule
import com.hb.pages.di.viewModelModule
import org.koin.android.ext.koin.androidContext

class MockApp : App() {

    override fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@MockApp)
            modules(
                listOf(
                    useCaseModule,
                    viewModelModule,
                    serviceModule,
                    mockApiModule,
                    mapperModule
                )
            )
        }
    }
}