package org.example.white.presentation.onBoarding

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class OnBoardingViewModel : ScreenModel {

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _onJoinChat = MutableSharedFlow<String>()
    val onJoinChat = _onJoinChat.asSharedFlow()

    fun onUsernameChange(username: String) {
        _usernameText.value = username
    }

    fun onJoinClick() {
        screenModelScope.launch {
            if (usernameText.value.isNotBlank()) {
                _onJoinChat.emit(usernameText.value)
            }
        }
    }
}