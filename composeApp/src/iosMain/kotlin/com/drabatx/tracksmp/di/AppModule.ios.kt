package com.drabatx.tracksmp.di

import com.drabatx.tracksmp.data.database.getDatabaseBuilder
import org.koin.dsl.module

actual val nativeModule = module {
    single { getDatabaseBuilder() }

}