package com.anddev404.aimtrainer.auth.data.remote

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest
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

    suspend fun signUp(email: String, password: String, nickname: String): Result<FirebaseUser> {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            auth.currentUser?.let { user ->
                val nickResult = addNickName(user, nickname)
                return@signUp if (nickResult.isSuccess) {
                    Result.success(user)
                } else {
                    Result.failure(
                        nickResult.exceptionOrNull() ?: Throwable("Failed to set nickname")
                    )
                }
            }
            return Result.failure(Throwable("User creation failed"))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    suspend fun addNickName(user: FirebaseUser, nickname: String): Result<Boolean> {
        return try {
            val profileUpdates = userProfileChangeRequest {
                displayName = nickname
            }
            user.updateProfile(profileUpdates).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}