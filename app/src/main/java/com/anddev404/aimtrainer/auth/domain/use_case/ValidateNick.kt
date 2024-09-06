package com.anddev404.aimtrainer.auth.domain.use_case

import com.anddev404.aimtrainer.auth.domain.errors.ErrorMessages
import com.anddev404.aimtrainer.auth.domain.model.ValidationResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateNick @Inject constructor(private val errorMessages: ErrorMessages) {

    fun execute(nick: String): ValidationResult {
        if (nick.isBlank()) {
            return ValidationResult(successful = false, errorMessage = errorMessages.emailBlank)
        }

        if (nick.length > 16) {
            return ValidationResult(successful = false, errorMessage = errorMessages.nickTooLong)
        }
        if (nick.length < 4) {
            return ValidationResult(successful = false, errorMessage = errorMessages.nickTooShort)
        }
        return ValidationResult(true)
    }
}