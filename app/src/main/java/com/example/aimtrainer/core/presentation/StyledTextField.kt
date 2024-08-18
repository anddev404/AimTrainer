package com.example.aimtrainer.core.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aimtrainer.ui.theme.DialogBoxMainTextColor
import com.example.aimtrainer.ui.theme.TextFieldContainerColor

@Composable
fun StyledTextField(
    value: TextFieldValue = TextFieldValue(),
    onValueChange: (TextFieldValue) -> Unit = {},
    isError: Boolean = false,
    supportingText: @Composable () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        isError = isError,
        supportingText = supportingText,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(16.dp),
        textStyle = TextStyle(fontSize = 24.sp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = TextFieldContainerColor,
            unfocusedContainerColor = TextFieldContainerColor,
            errorContainerColor = TextFieldContainerColor,
            errorIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.Gray,
            errorTextColor = Color.White,
            errorSupportingTextColor = DialogBoxMainTextColor
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun StyledTextFieldPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),

        ) {
        StyledTextField(
            value = TextFieldValue("example text"),
            isError = false
        )
        StyledTextField(
            isError = false
        )
        StyledTextField(
            isError = true,
            supportingText = { Text(text = "error") })
    }
}
