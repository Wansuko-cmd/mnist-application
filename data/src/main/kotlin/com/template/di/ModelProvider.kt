package com.template.di

import android.content.Context
import com.template.data.ml.MnistModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModelProvider {
    @Provides
    @Singleton
    fun providesMnistModel(
        @ApplicationContext context: Context,
    ): MnistModel = MnistModel.newInstance(context)
}
