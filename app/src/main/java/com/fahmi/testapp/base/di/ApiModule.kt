package com.fahmi.testapp.base.di

import com.fahmi.testapp.base.apiclient.ApiClient
import com.fahmi.testapp.base.apiclient.ApiClientImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ApiModule {

    @Binds
    internal abstract fun provideApi(apiClientImpl: ApiClientImpl): ApiClient
}