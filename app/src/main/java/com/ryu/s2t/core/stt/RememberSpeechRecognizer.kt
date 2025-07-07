package com.ryu.s2t.core.stt

import android.content.Context
import android.os.Build
import android.speech.SpeechRecognizer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

fun speechRecognizerSaver(context: Context): Saver<SpeechRecognizer, Unit> {
    return Saver(
        save = {
            // SpeechRecognizer 자체를 저장할 수는 없으므로 null 반환
            null
        },
        restore = {
            // 복원 시 새로운 SpeechRecognizer 객체 생성
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && SpeechRecognizer.isOnDeviceRecognitionAvailable(context)){
                SpeechRecognizer.createOnDeviceSpeechRecognizer(context)
            }else{
                SpeechRecognizer.createSpeechRecognizer(context)
            }
        }
    )
}

@Composable
fun rememberSpeechRecognizer(context: Context): SpeechRecognizer {
    return rememberSaveable(saver = speechRecognizerSaver(context)) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && SpeechRecognizer.isOnDeviceRecognitionAvailable(context)){
            SpeechRecognizer.createOnDeviceSpeechRecognizer(context)
        }else{
            SpeechRecognizer.createSpeechRecognizer(context)
        }
    }
}
