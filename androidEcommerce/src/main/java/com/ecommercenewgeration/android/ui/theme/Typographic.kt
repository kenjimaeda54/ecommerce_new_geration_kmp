package com.ecommercenewgeration.android.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.ecommercenewgeration.android.R.*
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp


val openSans = FontFamily(
    Font(font.opensans_bold, FontWeight.Bold),
    Font(font.opensans_light, FontWeight.Light),
    Font(font.opensans_regular, FontWeight.Normal),
    Font(font.opensans_semibold, FontWeight.SemiBold),
    Font(font.opensans_medium, FontWeight.Medium)

)

val typographic =  Typography(
    titleLarge = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 30.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 23.sp,
        lineHeight = 30.sp
    ),
    bodySmall = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Light,
        fontSize = 15.sp,
        lineHeight = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 25.sp
    ),

)