package com.anddev404.aimtrainer.di

import android.app.Application
import com.anddev404.aimtrainer.auth.data.remote.FirebaseAuthService
import com.anddev404.aimtrainer.auth.data.remote.FirebaseDatabaseService
import com.anddev404.aimtrainer.auth.data.repository.AuthRepositoryImpl
import com.anddev404.aimtrainer.auth.data.repository.DatabaseRepositoryImpl
import com.anddev404.aimtrainer.auth.domain.errors.ErrorMessages
import com.anddev404.aimtrainer.auth.domain.errors.ErrorMessagesImpl
import com.anddev404.aimtrainer.auth.domain.repository.AuthRepository
import com.anddev404.aimtrainer.auth.domain.repository.DatabaseRepository
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

    @Provides
    @Singleton
    fun provideFirebaseDatabaseService(): FirebaseDatabaseService {
        return FirebaseDatabaseService()
    }

    @Provides
    @Singleton
    fun provideDatabaseRepository(firebaseDatabaseService: FirebaseDatabaseService): DatabaseRepository {
        return DatabaseRepositoryImpl(firebaseDatabaseService)
    }
}