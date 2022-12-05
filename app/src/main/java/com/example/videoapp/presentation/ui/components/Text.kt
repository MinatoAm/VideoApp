package com.example.videoapp.presentation.ui.components

import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.videoapp.R

@Composable
fun EditTextColors(
    textColor: Color = colorResource(id = R.color.edit_text_color),
    disabledTextColor: Color = Color.White,
    backgroundColor: Color = Color.Transparent,
    cursorColor: Color = Color.Black,
    errorCursorColor: Color = Color.White,
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    disabledTextColor = disabledTextColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    errorCursorColor = errorCursorColor
)