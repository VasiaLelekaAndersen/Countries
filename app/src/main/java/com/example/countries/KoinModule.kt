package com.example.countries


import com.example.countries.networking.CountryApiInteractor
import com.example.countries.networking.CountryApiInterface
import com.example.countries.networking.CountryInteractorInterface
import com.example.countries.networking.PrettyJsonResponseLogger
import com.example.countries.repository.CountryRepository
import com.example.countries.repository.CountryRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { provideGson() }
    single { provideApiInterface(get()) }
    single { provideOkHttpClient() }
}

val countriesModule = module {
    factory<CountryInteractorInterface> { CountryApiInteractor(get()) }
    single<CountryRepository> {
        CountryRepositoryImpl()
    }
}

private fun provideOkHttpClient(): OkHttpClient {
    val prettyGson = PrettyJsonResponseLogger(provideGson())
    return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor(prettyGson).apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()
}

private fun provideApiInterface(retrofit: Retrofit): CountryApiInterface {
    return retrofit.create(CountryApiInterface::class.java)
}

internal fun provideGson(): Gson {
    return GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .create()
}
