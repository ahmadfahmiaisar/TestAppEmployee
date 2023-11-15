package com.fahmi.testapp.data.di

import com.fahmi.testapp.data.service.ServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideCustomerService(retrofit: Retrofit): ServiceApi =
        retrofit.create(ServiceApi::class.java)
}