package com.pedro0210.hobbylobby.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.pedro0210.hobbylobby.R



// all typoghoaphies
val bodyLarge = TextStyle(
    fontFamily = FontFamily(Font(R.font.abeezee_regular)),
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp
)

val titleLarge = TextStyle(
    fontFamily = FontFamily(Font(R.font.abeezee_regular)),
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
)

val labelSmall = TextStyle(
    fontFamily = FontFamily(Font(R.font.abeezee_regular)),
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)


// For the getter
val Typography = Typography(
    bodyLarge = bodyLarge,
    titleLarge = titleLarge,
    labelSmall = labelSmall
)
