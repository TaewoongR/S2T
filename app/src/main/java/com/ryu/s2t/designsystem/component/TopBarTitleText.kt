package com.ryu.s2t.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit

@Composable
fun TopBarTitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.typography.headlineMedium.color,
    fontSize: TextUnit = MaterialTheme.typography.headlineMedium.fontSize,
    fontStyle: FontStyle? = MaterialTheme.typography.headlineMedium.fontStyle,
    fontWeight: FontWeight? = MaterialTheme.typography.headlineMedium.fontWeight,
    fontFamily: FontFamily? = MaterialTheme.typography.headlineMedium.fontFamily,
    letterSpacing: TextUnit = MaterialTheme.typography.headlineMedium.letterSpacing,
    textAlign: TextAlign? = MaterialTheme.typography.headlineMedium.textAlign,
    textDecoration: TextDecoration? = MaterialTheme.typography.headlineMedium.textDecoration,
    lineHeight: TextUnit = MaterialTheme.typography.headlineMedium.lineHeight
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textAlign = textAlign,
        textDecoration = textDecoration,
        lineHeight = lineHeight
    )
}

@Preview
@Composable
fun TopBarTitleTextPreview() {
    TopBarTitleText(text = "Title", modifier = Modifier.background(Color.White))
}
