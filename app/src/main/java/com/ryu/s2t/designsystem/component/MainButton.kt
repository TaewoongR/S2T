package com.ryu.s2t.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryu.s2t.designsystem.theme.RecordAppTheme

@Composable
fun MainButton(
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
    contentPadding: PaddingValues = PaddingValues(18.dp),
    interactionSource: MutableInteractionSource? = null,
    text: String = ""
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
        Text(
            text = text,
            color = if (enabled) colors.contentColor else colors.disabledContentColor,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            fontStyle = MaterialTheme.typography.labelLarge.fontStyle,
            fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
            fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
            letterSpacing = MaterialTheme.typography.labelLarge.letterSpacing,
            textAlign = MaterialTheme.typography.labelLarge.textAlign
        )
    }
}

@Preview
@Composable
fun MainButtonPreview() {
    RecordAppTheme {
        MainButton(
            onClick = {},
            modifier = Modifier.width(288.dp),
            text = "메인버튼"
        )
    }
}

@Preview
@Composable
fun MainButtonDisabledPreview() {
    RecordAppTheme {
        MainButton(
            onClick = {},
            modifier = Modifier.width(288.dp),
            enabled = false,
            text = "메인버튼"
        )
    }
}
