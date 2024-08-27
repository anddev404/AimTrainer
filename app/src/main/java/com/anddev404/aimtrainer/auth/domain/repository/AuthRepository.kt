package com.anddev404.aimtrainer.auth.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signIn(email: String, password: String): Result<FirebaseUser>

    fun signOut()

    fun getCurrentUser(): FirebaseUser?

    suspend fun signUp(email: String, password: String, nick: String): Result<FirebaseUser>

    suspend fun addNickName(user: FirebaseUser, nickname: String)
}