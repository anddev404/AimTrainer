package com.example.aimtrainer.di

import android.app.Application
import com.example.aimtrainer.auth.data.remote.FirebaseAuthService
import com.example.aimtrainer.auth.data.repository.AuthRepositoryImpl
import com.example.aimtrainer.auth.domain.errors.ErrorMessages
import com.example.aimtrainer.auth.domain.errors.ErrorMessagesImpl
import com.example.aimtrainer.auth.domain.repository.AuthRepository
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

    @Provides
    @Singleton
    fun provideFirebaseAuthService(): FirebaseAuthService {
        return FirebaseAuthService()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuthService: FirebaseAuthService): AuthRepository {
        return AuthRepositoryImpl(firebaseAuthService)
    }
}