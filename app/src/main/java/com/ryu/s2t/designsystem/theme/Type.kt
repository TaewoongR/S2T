package com.ryu.s2t.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val defaultFontFamily = FontFamily.Default

val Typography = Typography(

    // Top Bar Title
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    // Title
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp
    ),

    // Subtitle
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp
    ),

    titleSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp
    ),

    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp
    ),

    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp
    ),

    // Button text
    labelLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.32).sp
    ),

    // Label
    labelMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 17.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.5).sp
    ),

    // SubLabel
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 21.sp,
        letterSpacing = (-0.32).sp
    )
)
