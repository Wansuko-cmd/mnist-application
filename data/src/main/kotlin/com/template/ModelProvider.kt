package com.template

import android.content.Context
import com.template.data.ml.MnistModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ModelProvider {
    @Provides
    fun providesMnistModel(
        @ApplicationContext context: Context,
    ): MnistModel = MnistModel.newInstance(context)
}
