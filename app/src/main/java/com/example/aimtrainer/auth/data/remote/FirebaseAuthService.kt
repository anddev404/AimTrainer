package com.example.aimtrainer.auth.data.remote

import com.example.aimtrainer.auth.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class FirebaseAuthService {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun sighIn(email: String, password: String): Result<User> {
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

    fun getCurrentUser(): User? {
        auth.currentUser?.let {
            return@getCurrentUser User(it.uid, it.email ?: "", it.displayName ?: "")
        }
        return null
    }
}