package com.caglaakgul.ecabsandroidchallenge.di

import com.caglaakgul.ecabsandroidchallenge.BuildConfig
import com.caglaakgul.ecabsandroidchallenge.data.remote.ApiService
import com.caglaakgul.ecabsandroidchallenge.data.remote.HttpTokenInterceptor
import com.caglaakgul.ecabsandroidchallenge.data.remote.NetworkConnectionInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    @Singleton
    @Provides
    fun provideHttpClient(
        httpTokenInterceptor: HttpTokenInterceptor,
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            connectTimeout(2, TimeUnit.MINUTES)
            readTimeout(2, TimeUnit.MINUTES)
            writeTimeout(2, TimeUnit.MINUTES)
            addInterceptor(httpTokenInterceptor)
            addInterceptor(networkConnectionInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}