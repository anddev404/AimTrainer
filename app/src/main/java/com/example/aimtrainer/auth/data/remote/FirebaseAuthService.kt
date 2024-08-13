package com.example.aimtrainer.auth.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class FirebaseAuthService {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun sighIn(email: String, password: String): Result<FirebaseUser> {
        try {

            auth.signInWithEmailAndPassword(email, password).await()

            getCurrentUser()?.let {
                return@sighIn Result.success(it)
            }
            return Result.failure(Throwable())

        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}