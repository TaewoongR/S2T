package com.ryu.s2t.feature.record

import com.ryu.s2t.feature.base.UiEffect

sealed class RecordEffect : UiEffect {
    data class NavigateToDiaryDrawing(val diaryContent: String) : RecordEffect()
    data object NavigateToBack : RecordEffect()
    data object StartRecording : RecordEffect()
    data object ReRecord : RecordEffect()
    data object StopRecording : RecordEffect()
    data class ShowMessage(val message: String) : RecordEffect()
}
