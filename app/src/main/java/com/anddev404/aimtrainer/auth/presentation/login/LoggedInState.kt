package com.anddev404.aimtrainer.auth.presentation.login

import com.google.firebase.auth.FirebaseUser

data class LoggedInState(
    val loggingInProgress: Boolean = false,
    val user: FirebaseUser? = null,
    val error: String? = null
)
