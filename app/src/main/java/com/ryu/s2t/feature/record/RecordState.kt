package com.ryu.s2t.feature.record

import com.ryu.s2t.feature.base.UiState
import javax.annotation.concurrent.Immutable

@Immutable
data class RecordState(
    val isRecordPermissionGranted: Boolean = false,
    val isRecording: Boolean = false,
    val isErrorOccurred: Boolean = false,
    val isAudioPermissionGranted: Boolean = false,
    val resultText: String = "",
    val tempText: String = ""
) : UiState
