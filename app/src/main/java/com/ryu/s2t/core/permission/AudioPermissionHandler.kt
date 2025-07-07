package com.ryu.s2t.core.permission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.ryu.s2t.designsystem.component.PermissionAlertDialog
import kotlin.apply
import kotlin.collections.getValue

@Composable
fun AudioPermissionHandler(
    isPermissionGranted: Boolean,
    updatePermission: (Boolean) -> Unit
) {
    val context = LocalContext.current
    var showDialog by rememberSaveable { mutableStateOf(false) }
    val audioPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val isGranted = permissions.getValue(Manifest.permission.RECORD_AUDIO)
        if (isGranted) {
            updatePermission(true)
        } else {
            showDialog = true
        }
    }

    LaunchedEffect(isPermissionGranted) {
        // 오디오 녹음 권한
        val isRecordAudioPermissionGranted = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED

        if (!isPermissionGranted && !isRecordAudioPermissionGranted) {
            // 오디오 녹음 권한이 허용되지 않은 경우 권한을 요청
            audioPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.RECORD_AUDIO
                )
            )
        } else {
            // 오디오 녹음 권한이 허용된 경우 권한 상태를 업데이트
            updatePermission(true)
        }
    }

    if (showDialog) {
        PermissionAlertDialog(
            permissionType = "오디오 녹음",
            intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
            },
            onShowDialog = { showDialog = it }
        )
    }
}
