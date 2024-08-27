package com.anddev404.aimtrainer.auth.domain.use_case

import com.anddev404.aimtrainer.auth.domain.errors.ErrorMessages
import com.anddev404.aimtrainer.auth.domain.model.ValidationResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidatePassword @Inject constructor(private val errorMessages: ErrorMessages) {

    fun execute(password: String): ValidationResult {

        if (password.isBlank()) {
            return ValidationResult(successful = false, errorMessage = errorMessages.passwordBlank)
        }

        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = errorMessages.passwordTooShort
            )
        }
        return ValidationResult(true)
    }
}