package com.ryu.s2t.designsystem.component

import android.content.Intent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun PermissionAlertDialog(permissionType: String, intent: Intent, onShowDialog: (Boolean) -> Unit) {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                onShowDialog(false)
            },
            title = {
                Text("권한 요청")
            },
            text = {
                Text("앱의 원할한 사용을 위해서는 $permissionType 접근 권한이 필요합니다. 다시 설정하시겠습니까?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        onShowDialog(false)
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text("설정")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text("거부")
                }
            }
        )
    }
}
