package org.example.white.presentation.onBoarding

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.example.white.data.remote.ApiService
import org.example.white.domain.repositories.AuthRepository
import org.example.white.domain.repositories.PreferencesRepository
import org.example.white.presentation.ParentViewModel
import org.example.white.util.Constants

class OnBoardingViewModel(
    val authRepository: AuthRepository,
    val preferencesRepository: PreferencesRepository
) : ParentViewModel() {
    private val _uiEvent = Channel<OnBoardingUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun login(displayName: String, password: String) {
        screenModelScope.launch {
            val resp = authRepository.login(displayName, password)
            if (resp.userId != null) {
                preferencesRepository.setValue(Constants.MY_LOGGED_IN_ID, resp.userId)
                _uiEvent.send(OnBoardingUiEvent.Success)

            } else {
                _uiEvent.send(OnBoardingUiEvent.Error("Log in error"))
            }
        }
    }
}