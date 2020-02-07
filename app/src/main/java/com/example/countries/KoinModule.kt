package com.example.countries


import com.example.countries.networking.CountryApiInteractor
import com.example.countries.networking.CountryApiInterface
import com.example.countries.repository.CountryRepository
import com.example.countries.repository.CountryRepositoryImpl
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

    single { provideApiInterface(get()) }
    single { provideOkHttpClient() }
}

val countriesModule = module {
    single { CountryApiInteractor(get()) }
    single<CountryRepository> {
        CountryRepositoryImpl()
    }
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()
}

private fun provideApiInterface(retrofit: Retrofit): CountryApiInterface {
    return retrofit.create(CountryApiInterface::class.java)
}
