package com.wsr.mnist.di

import com.wsr.mnist.repository.MnistRepository
import com.wsr.mnist.repository.MnistRepositoryImpl
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
