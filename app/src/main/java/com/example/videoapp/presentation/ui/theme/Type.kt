package com.example.videoapp.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.videoapp.R

val OpenSans = FontFamily(
    Font( R.font.opensans_semibold)
)
val Roboto = FontFamily(
    Font( R.font.roboto_regular)
)
val Typography = Typography(
    button = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 23.sp,
        letterSpacing = 0.5.sp
    ),
    h1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.5.sp
    )
)