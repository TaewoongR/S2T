package com.ryu.s2t.feature.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<UI_EVENT : UiEvent, UI_STATE : UiState, UI_EFFECT : UiEffect>(
    initialState: UI_STATE
) : ViewModel() {

    private val _state = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _intent = MutableSharedFlow<UI_EVENT>()
    val intent = _intent.asSharedFlow()

    private val _uiEffect: Channel<UiEffect> = Channel()
    val uiEffect = _uiEffect.receiveAsFlow()

    protected val currentState: UI_STATE
        get() = _state.value

    fun sendIntent(intent: UI_EVENT) {
        viewModelScope.launch { _intent.emit(intent) }
    }

    protected fun sendState(reduce: UI_STATE.() -> UI_STATE) {
        _state.update { currentState.reduce() }
    }

    protected fun sendEffect(builder: () -> UI_EFFECT) {
        val effectValue = builder()
        viewModelScope.launch { _uiEffect.send(effectValue) }
    }
}
