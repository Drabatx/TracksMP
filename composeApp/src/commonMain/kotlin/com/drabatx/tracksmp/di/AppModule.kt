package com.drabatx.tracksmp.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.drabatx.tracksmp.data.database.TrackDao
import com.drabatx.tracksmp.data.database.TracksDatabase
import com.drabatx.tracksmp.data.database.UserDao
import com.drabatx.tracksmp.data.domain.repository.AutenticateRepository
import com.drabatx.tracksmp.data.domain.repository.AutenticateRepositoryImpl
import com.drabatx.tracksmp.data.domain.repository.LoginRepository
import com.drabatx.tracksmp.data.domain.repository.LoginRepositoryImpl
import com.drabatx.tracksmp.data.domain.repository.TracksRepository
import com.drabatx.tracksmp.data.domain.repository.TracksRepositoryImpl
import com.drabatx.tracksmp.data.domain.usecase.GetCurrentUserUseCase
import com.drabatx.tracksmp.data.domain.usecase.GetTrackByIdUseCase
import com.drabatx.tracksmp.data.domain.usecase.GetTrackListUseCase
import com.drabatx.tracksmp.data.domain.usecase.IsLoggedUseCase
import com.drabatx.tracksmp.data.domain.usecase.LogOutUseCase
import com.drabatx.tracksmp.data.domain.usecase.LoginUseCase
import com.drabatx.tracksmp.data.network.ApiService
import com.drabatx.tracksmp.presentation.view.viewmodels.LoginViewModel
import com.drabatx.tracksmp.presentation.view.viewmodels.TrackViewModel
import com.drabatx.tracksmp.presentation.view.viewmodels.TrackDetailViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val databaseModule = module {
    single<UserDao> {
        val dbBuilder = get<RoomDatabase.Builder<TracksDatabase>>()
        dbBuilder.setDriver(BundledSQLiteDriver()).build().userDao()
    }

    single<TrackDao> {
        val dbBuilder = get<RoomDatabase.Builder<TracksDatabase>>()
        dbBuilder.setDriver(BundledSQLiteDriver()).build().trackDao()
    }
}

val appModule = module {
    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                }, contentType = ContentType.Any)
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTP
                    host = "apk.ctn.smapps.mx"
                    port = 9582
                }
            }
        }
    }

    single<ApiService> { ApiService(get()) }


    factory<LoginRepository> {
        LoginRepositoryImpl(get())
    }
    factoryOf(::LoginUseCase)

    factory<AutenticateRepository> { AutenticateRepositoryImpl(get()) }
    factoryOf(::IsLoggedUseCase)
    factoryOf(::LogOutUseCase)
    factory<TracksRepository> { TracksRepositoryImpl(get(), get()) }
    factoryOf(::GetTrackListUseCase)
    factoryOf(::GetCurrentUserUseCase)
    factoryOf(::GetTrackByIdUseCase)
}
val viewModels = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::TrackViewModel)
    viewModelOf(::TrackDetailViewModel)
}
expect val nativeModule: Module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(databaseModule, appModule, viewModels, nativeModule)
    }
}
