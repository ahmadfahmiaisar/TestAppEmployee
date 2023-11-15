package com.fahmi.testapp.data.di

import com.fahmi.testapp.data.repository.RepositoryImpl
import com.fahmi.testapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun bindCustomerRepository(repository: RepositoryImpl): Repository
}