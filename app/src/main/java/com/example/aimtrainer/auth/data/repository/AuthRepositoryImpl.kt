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

    override suspend fun signUp(
        email: String,
        password: String,
        nickname: String
    ): Result<FirebaseUser> {
        return firebaseAuthService.signUp(email, password, nickname)
    }

    override suspend fun addNickName(user: FirebaseUser, nickname: String) {
        firebaseAuthService.addNickName(user, nickname)
    }
}