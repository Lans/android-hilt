package com.example.android.hilt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * author:       lans
 * date:         2022/7/11 14:26
 * description:
 **/
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var testModel: TestModel


    val intent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.BeforeShow)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.DismissLog -> {
                        _state.value = MainState.Dismiss
                    }
                    is MainIntent.ShowLog -> {
                        _state.value = MainState.Show
                    }
                }
            }
        }
    }

    fun check() {
        testModel.printLog()
    }

//    fun show() {
//        state.value = 1
//    }
//
//    fun dismiss() {
//        state.value = 2
//    }
}

sealed class MainIntent {
    data class ShowLog(val num: Int) : MainIntent()
    object DismissLog : MainIntent()
}

sealed class MainState {
    object BeforeShow : MainState()
    object Show : MainState()
    object Dismiss : MainState()
}