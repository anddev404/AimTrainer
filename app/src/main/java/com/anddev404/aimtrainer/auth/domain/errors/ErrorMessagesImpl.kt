package com.anddev404.aimtrainer.auth.domain.errors

import android.content.res.Resources
import com.anddev404.aimtrainer.R

class ErrorMessagesImpl(private val resources: Resources) : ErrorMessages {
    override val emailBlank: String
        get() = resources.getString(R.string.blank_email)

    override val emailNotValid: String
        get() = resources.getString(R.string.invalid_email)

    override val emailAlreadyInUse: String
        get() = resources.getString(R.string.email_already_in_use)

    override val passwordBlank: String
        get() = resources.getString(R.string.blank_password)

    override val passwordTooShort: String
        get() = resources.getString(R.string.too_short_password)

    override val passwordComplexityError: String
        get() = resources.getString(R.string.complexity_error_password)

    override val passwordMismatch: String
        get() = resources.getString(R.string.mismatch_password)

    override val passwordSameAsOld: String
        get() = resources.getString(R.string.same_as_old_password)

    override val nickBlank: String
        get() = resources.getString(R.string.blank_nick)

    override val nickTooShort: String
        get() = resources.getString(R.string.too_short_nick)

    override val nickTooLong: String
        get() = resources.getString(R.string.too_long_nick)
}