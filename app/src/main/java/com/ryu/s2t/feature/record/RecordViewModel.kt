package com.ryu.s2t.feature.record

import android.speech.SpeechRecognizer
import androidx.lifecycle.viewModelScope
import com.ryu.s2t.core.stt.VDRecognitionListener
import com.ryu.s2t.feature.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor() : BaseViewModel<RecordEvent, RecordState, RecordEffect>(
    RecordState()
) {
    @Volatile
    private var result = ""

    @Volatile
    private var partialTextFlag: Boolean = false

    init {
        reducer()
    }

    val recognitionListener = VDRecognitionListener(
        onResult = {
            sendState {
                currentState.copy(
                    isRecording = true,
                    isErrorOccurred = true
                )
            }
            sendEffect { RecordEffect.ReRecord }
        },
        onVoicePartialExtracted = { recorded ->
            if (partialTextFlag && recorded[0].isBlank()) {
                result += currentState.tempText
                sendState {
                    currentState.copy(
                        resultText = result,
                        tempText = recorded[0]
                    )
                }
                partialTextFlag = false
            } else {
                sendState {
                    currentState.copy(
                        resultText = result + recorded[0],
                        tempText = recorded[0]
                    )
                }
            }
        },
        onReady = {
            if (!currentState.isErrorOccurred) {
                sendEffect { RecordEffect.ShowMessage("녹음을 시작합니다.") }
            }
        },
        onEnd = {
            partialTextFlag = true
        },
        onErrorInt = { errorInt ->
            when (errorInt) {
                5, 7 -> {
                    sendState {
                        currentState.copy(
                            isRecording = true,
                            isErrorOccurred = true
                        )
                    }
                    sendEffect { RecordEffect.ReRecord }
                }
                SpeechRecognizer.ERROR_NETWORK -> {
                    sendState {
                        currentState.copy(
                            isRecording = false,
                            isErrorOccurred = false
                        )
                    }
                    sendEffect { RecordEffect.ShowMessage("네트워크 연결이 필요합니다.") }
                }
                else -> {
                    sendState {
                        currentState.copy(
                            isRecording = false,
                            isErrorOccurred = false
                        )
                    }
                    sendEffect { RecordEffect.ShowMessage("녹화에 문제가 생겼습니다.") }
                }
            }
        }
    )

    private fun reducer() {
        viewModelScope.launch {
            intent.collect { intent ->
                when (intent) {
                    is RecordEvent.RequestRecordPermission -> {
                        requestRecordPermission(intent.isGranted)
                    }

                    RecordEvent.ClickBackButton -> clickBackButton()
                    RecordEvent.StartRecording -> startRecording()
                    RecordEvent.StopRecording -> stopRecording()
                    RecordEvent.ReturnToWritingScreen -> {
                        sendState {
                            currentState.copy(
                                resultText = result
                            )
                        }
                    }
                    is RecordEvent.DownloadedAsTxt -> {
                        if (intent.isDownloaded) {
                            sendEffect { RecordEffect.ShowMessage("텍스트 파일로 저장되었습니다.") }
                        } else {
                            sendEffect { RecordEffect.ShowMessage("텍스트 파일 저장을 실패했습니다.") }
                        }
                    }
                }
            }
        }
    }

    private fun requestRecordPermission(isGranted: Boolean) {
        sendState { RecordState(isAudioPermissionGranted = isGranted) }
    }

    private fun clickBackButton() {
        sendEffect { RecordEffect.NavigateToBack }
    }

    private fun startRecording() {
        sendState { currentState.copy(isRecording = true) }
        sendEffect { RecordEffect.StartRecording }
    }

    private fun stopRecording() {
        result = currentState.resultText
        sendState {
            currentState.copy(
                isRecording = false,
                isErrorOccurred = false,
                tempText = ""
            )
        }
        sendEffect { RecordEffect.StopRecording }
        sendEffect { RecordEffect.ShowMessage("녹음이 종료되었습니다.") }
    }
}
