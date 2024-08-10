package com.example.aimtrainer.auth.domain.repository

import com.example.aimtrainer.auth.domain.model.User

interface AuthRepository {

    suspend fun signIn(email: String, password: String): Result<User>

    fun signOut()

    fun getCurrentUser(): User?
}