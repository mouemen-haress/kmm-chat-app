package org.example.white.presentation.onBoarding

sealed class OnBoardingUiEvent {
    object Success : OnBoardingUiEvent()
    data class Error(var errorText: String) : OnBoardingUiEvent()

}
