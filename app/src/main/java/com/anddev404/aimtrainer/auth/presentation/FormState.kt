package com.anddev404.aimtrainer.auth.presentation

import androidx.compose.ui.text.input.TextFieldValue
import com.anddev404.aimtrainer.auth.domain.model.ValidationResult

data class FormState(
    val textFieldValue: TextFieldValue = TextFieldValue(),
    val validationResult: ValidationResult? = null
)