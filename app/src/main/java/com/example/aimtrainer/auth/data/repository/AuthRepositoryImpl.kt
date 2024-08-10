package com.example.aimtrainer.auth.data.repository

import com.example.aimtrainer.auth.data.remote.FirebaseAuthService
import com.example.aimtrainer.auth.domain.model.User
import com.example.aimtrainer.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(private val firebaseAuthService: FirebaseAuthService) : AuthRepository {
    
    override suspend fun signIn(email: String, password: String): Result<User> {
        return firebaseAuthService.sighIn(email, password)
    }

    override fun signOut() {
        firebaseAuthService.signOut()
    }

    override fun getCurrentUser(): User? {
        return firebaseAuthService.getCurrentUser()
    }
}