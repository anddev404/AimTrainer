package com.example.aimtrainer.auth.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signIn(email: String, password: String): Result<FirebaseUser>

    fun signOut()

    fun getCurrentUser(): FirebaseUser?
}