package com.example.aimtrainer.auth.data.repository

import com.example.aimtrainer.auth.data.remote.FirebaseAuthService
import com.example.aimtrainer.auth.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthRepositoryImpl(private val firebaseAuthService: FirebaseAuthService) : AuthRepository {

    override suspend fun signIn(email: String, password: String): Result<FirebaseUser> {
        return firebaseAuthService.sighIn(email, password)
    }

    override fun signOut() {
        firebaseAuthService.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuthService.getCurrentUser()
    }
}