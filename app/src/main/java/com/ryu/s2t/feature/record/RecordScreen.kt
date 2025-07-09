package com.ryu.s2t.feature.record

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.ryu.s2t.R
import com.ryu.s2t.core.permission.AudioPermissionHandler
import com.ryu.s2t.core.stt.getSttIntent
import com.ryu.s2t.core.stt.rememberSpeechRecognizer
import com.ryu.s2t.designsystem.component.TopBarTitleText
import com.ryu.s2t.designsystem.component.VDCircleIconButton
import kotlinx.coroutines.flow.buffer
import timber.log.Timber

@Composable
fun DiaryWritingRoute(
    finishApp: () -> Unit,
    viewModel: RecordViewModel = hiltViewModel()
) {
    val context = LocalContext.current.applicationContext
    val uiState by viewModel.state.collectAsState()
    val speechRecognizer = rememberSpeechRecognizer(context)
    val intent = getSttIntent()
    var backPressedTime by remember { mutableLongStateOf(0L) }
    var isSpeechRecognizerDestroyed by remember { mutableStateOf(false) }

    fun destroySpeechRecognizer() {
        if (!isSpeechRecognizerDestroyed) {
            try {
                speechRecognizer.destroy()
            } catch (e: IllegalArgumentException) {
                // 이미 unbind된 경우 예외가 발생하므로 무시하거나 로그 처리
                Timber.e(e, "SpeechRecognizer destroy called when service not registered")
            }
            isSpeechRecognizerDestroyed = true
        }
    }

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime > 2000L) {
            backPressedTime = currentTime
            Toast.makeText(context, R.string.finish_app_warning, Toast.LENGTH_SHORT).show()
        } else {
            viewModel.sendIntent(RecordEvent.ClickBackButton)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.buffer(2).collect { effect ->
            when (effect) {
                is RecordEffect.StartRecording -> {
                    if (uiState.isRecording) {
                        speechRecognizer.setRecognitionListener(viewModel.recognitionListener)
                        speechRecognizer.startListening(intent)
                        isSpeechRecognizerDestroyed = false
                    }
                }

                is RecordEffect.ReRecord -> {
                    if (uiState.isErrorOccurred) {
                        speechRecognizer.startListening(intent)
                        isSpeechRecognizerDestroyed = false
                    }
                }

                is RecordEffect.StopRecording -> {
                    if (!uiState.isRecording) {
                        speechRecognizer.stopListening()
                        destroySpeechRecognizer()
                    }
                }

                is RecordEffect.NavigateToBack -> {
                    destroySpeechRecognizer()
                    finishApp()
                }

                is RecordEffect.NavigateToDiaryDrawing -> {
                    destroySpeechRecognizer()
                }

                is RecordEffect.ShowMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    AudioPermissionHandler(
        isPermissionGranted = uiState.isAudioPermissionGranted,
        updatePermission = { isGranted ->
            viewModel.sendIntent(RecordEvent.RequestRecordPermission(isGranted))
        }
    )

    DiaryWritingScreen(
        uiState = uiState,
        onBackClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - backPressedTime > 2000L) {
                backPressedTime = currentTime
                Toast.makeText(context, R.string.finish_app_warning, Toast.LENGTH_SHORT).show()
            } else {
                viewModel.sendIntent(RecordEvent.ClickBackButton)
            }
        },
        onSaveClick = {
            saveTextFileToMediaStore(
                context = context,
                text = uiState.resultText,
                isComplete = { viewModel.sendIntent(RecordEvent.DownloadedAsTxt(it)) }
            )
        },
        onStartRecord = { viewModel.sendIntent(RecordEvent.StartRecording) },
        onStopRecord = { viewModel.sendIntent(RecordEvent.StopRecording) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryWritingScreen(
    uiState: RecordState,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onStartRecord: () -> Unit,
    onStopRecord: () -> Unit
) {
    val scrollState = rememberScrollState()

    LaunchedEffect(uiState.resultText) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TopBarTitleText(stringResource(R.string.record_screen_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back_arrow),
                            contentDescription = "뒤로가기 버튼"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onSaveClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_download),
                            contentDescription = "다운로드 버튼"
                        )
                    }
                },
                windowInsets = WindowInsets(0.dp),
                colors = TopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    actionIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {
            val (content, button) = createRefs()

            Column(
                modifier = Modifier
                    .constrainAs(content) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(button.top)
                        height = Dimension.fillToConstraints
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 16.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = uiState.resultText,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Column(
                modifier = Modifier
                    .constrainAs(button) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.width(210.dp),
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    VDCircleIconButton(
                        onClick = { onStartRecord() },
                        resInt = R.drawable.ic_record,
                        text = stringResource(R.string.record_start_button),
                        enabled = !uiState.isRecording,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    VDCircleIconButton(
                        onClick = { onStopRecord() },
                        resInt = R.drawable.ic_stop,
                        text = stringResource(R.string.record_stop_button),
                        enabled = uiState.isRecording,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}
