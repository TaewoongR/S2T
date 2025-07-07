package com.ryu.s2t.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun VDIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(8.dp),
    colors: ButtonColors = ButtonColors(
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onSurface,
        disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
        disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ),
    elevation: ButtonElevation? = null,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(
        top = 8.dp,
        bottom = 16.dp,
        start = 8.dp,
        end = 8.dp
    ),
    interactionSource: MutableInteractionSource? = null,
    text: String = "",
    icon: Int,
    iconDisable: Int
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = shape,
        colors = colors,
        elevation = elevation,
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                painter = painterResource(id = if (enabled) icon else iconDisable),
                contentDescription = "Diary Icon",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                color = colors.contentColor,
                fontSize = MaterialTheme.typography.labelMedium.fontSize,
                fontStyle = MaterialTheme.typography.labelMedium.fontStyle,
                fontWeight = MaterialTheme.typography.labelMedium.fontWeight,
                fontFamily = MaterialTheme.typography.labelMedium.fontFamily,
                letterSpacing = MaterialTheme.typography.labelMedium.letterSpacing,
                textAlign = MaterialTheme.typography.labelMedium.textAlign
            )
        }
    }
}
