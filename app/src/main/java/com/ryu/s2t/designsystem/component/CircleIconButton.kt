package com.ryu.s2t.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryu.s2t.R
import com.ryu.s2t.designsystem.theme.RecordAppTheme

@Composable
fun VDCircleIconButton(
    onClick: () -> Unit,
    resInt: Int,
    text: String,
    enabled: Boolean = true,
    color: Color = Color.Unspecified
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(75.dp)
                .clip(shape = CircleShape)
                .background(Color.White)
                .clickable(
                    enabled = enabled,
                    onClick = onClick
                )
        ) {
            Icon(
                painter = painterResource(id = resInt),
                contentDescription = "버튼 이미지",
                modifier = Modifier.align(Alignment.Center),
                tint = if (enabled) color else color.copy(alpha = 0.5f)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = if (enabled) color else color.copy(alpha = 0.5f)
        )
    }
}

@Preview
@Composable
fun RecordButtonPreview() {
    RecordAppTheme {
        VDCircleIconButton(
            onClick = {},
            resInt = R.drawable.ic_record,
            text = "기록 시작"
        )
    }
}

@Preview
@Composable
fun StopButtonPreview() {
    RecordAppTheme {
        VDCircleIconButton(
            onClick = {},
            resInt = R.drawable.ic_stop,
            text = "기록 중지"
        )
    }
}
