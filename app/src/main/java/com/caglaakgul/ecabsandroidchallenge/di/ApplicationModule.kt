package com.caglaakgul.ecabsandroidchallenge.di

import android.content.Context
import com.caglaakgul.ecabsandroidchallenge.data.remote.ApiService
import com.caglaakgul.ecabsandroidchallenge.data.repository.EventsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Singleton
    @Provides
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        api: ApiService
    ) = EventsRepository(
        api
    )
}