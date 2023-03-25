package com.template.di

import com.template.repository.MnistRepository
import com.template.repository.MnistRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBindModule {
    @Binds
    fun bindMnistRepository(
        mnistRepositoryImpl: MnistRepositoryImpl,
    ): MnistRepository
}
