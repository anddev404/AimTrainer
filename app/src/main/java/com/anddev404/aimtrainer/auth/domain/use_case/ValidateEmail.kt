package com.anddev404.aimtrainer.auth.domain.use_case

import android.util.Patterns
import com.anddev404.aimtrainer.auth.domain.errors.ErrorMessages
import com.anddev404.aimtrainer.auth.domain.model.ValidationResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateEmail @Inject constructor(private val errorMessages: ErrorMessages) {

    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(successful = false, errorMessage = errorMessages.emailBlank)
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(successful = false, errorMessage = errorMessages.emailNotValid)

        }
        return ValidationResult(true)
    }
}