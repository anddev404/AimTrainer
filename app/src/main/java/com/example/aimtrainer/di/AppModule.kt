package com.example.aimtrainer.di

import android.app.Application
import com.example.aimtrainer.auth.domain.use_case.ErrorMessages
import com.example.aimtrainer.auth.domain.use_case.ErrorMessagesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideErrorMessages(app: Application): ErrorMessages {
        return ErrorMessagesImpl(app.resources)
    }
}