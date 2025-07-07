package com.ryu.s2t.core.stt

import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import timber.log.Timber

class VDRecognitionListener(
    private val onResult: () -> Unit,
    private val onVoicePartialExtracted: (recorded: ArrayList<String>) -> Unit,
    private val onReady: () -> Unit,
    private val onEnd: () -> Unit,
    private val onErrorInt: (Int) -> Unit
) : RecognitionListener {

    override fun onReadyForSpeech(params: Bundle?) {
        onReady()
        Timber.d("Speech recognizer is ready for speech.")
    }

    override fun onBeginningOfSpeech() {}

    override fun onRmsChanged(rmsdB: Float) {}

    override fun onBufferReceived(buffer: ByteArray?) {}

    override fun onEndOfSpeech() {
        onEnd()
    }

    override fun onError(error: Int) {
        onErrorInt(error)
        Timber.e("Speech recognizer error: $error")
        when (error) {
            SpeechRecognizer.ERROR_NETWORK -> Timber.e("Network error occurred.")
            SpeechRecognizer.ERROR_NO_MATCH -> Timber.e("No match found.")
            SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> Timber.e("Speech timeout occurred.")
            else -> Timber.e("An unknown error occurred: $error")
        }
    }

    override fun onResults(results: Bundle) {
        onResult()
    }

    override fun onPartialResults(partialResults: Bundle?) {
        val recorded = partialResults?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)

        if (!recorded.isNullOrEmpty()) {
            Timber.d("Partial Results: $recorded")
            onVoicePartialExtracted(recorded)
        }
    }

    override fun onEvent(eventType: Int, params: Bundle?) {}
}
