package com.example.onlineshopsat.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.example.onlineshopsat.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.7.sp
    ),
    titleMedium = TextStyle(
        color = HeadTextColor,
        fontSize = 32.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle(R.font.montserrat_black),
        letterSpacing = 0.7.sp
    ),
    bodyMedium = TextStyle(
        color = Color.Black,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle(R.font.montserrat_black),
        letterSpacing = 0.7.sp
    ),
    labelSmall = TextStyle(
        color = TextButtonColor,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle(R.font.montserrat_black),
        letterSpacing = 0.7.sp
    ),
    labelMedium = TextStyle(
        color = TextLabelColor,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle(R.font.montserrat_black),
        letterSpacing = 0.7.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)