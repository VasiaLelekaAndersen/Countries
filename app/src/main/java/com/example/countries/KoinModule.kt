package com.example.countries


import com.example.countries.networking.ApiInteractor
import com.example.countries.networking.ApiInterface
import com.example.countries.networking.ApiWrapper
import com.example.countries.networking.BASE_URL
import com.example.countries.repository.Repository
import com.example.countries.repository.RepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<Repository> {
        RepositoryImpl()
    }
    single { provideApiInterface(get()) }
    single { provideOkHttpClient() }
    single { ApiWrapper(get()) }
    single { ApiInteractor(get()) }
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()
}

private fun provideApiInterface(retrofit: Retrofit): ApiInterface {
    return retrofit.create(ApiInterface::class.java)
}
