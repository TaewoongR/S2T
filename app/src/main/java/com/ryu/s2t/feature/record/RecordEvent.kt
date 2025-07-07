package com.ryu.s2t.feature.record

import com.ryu.s2t.feature.base.UiEvent

sealed class RecordEvent : UiEvent {
    data class RequestRecordPermission(val isGranted: Boolean) : RecordEvent()
    data class DownloadedAsTxt(val isDownloaded: Boolean) : RecordEvent()
    data object ReturnToWritingScreen : RecordEvent()
    data object StartRecording : RecordEvent()
    data object StopRecording : RecordEvent()
    data object ClickBackButton : RecordEvent()
}
