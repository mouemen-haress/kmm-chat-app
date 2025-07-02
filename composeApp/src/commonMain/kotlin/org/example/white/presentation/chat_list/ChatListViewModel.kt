package org.example.white.presentation.chat_list

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.white.domain.repositories.ChatRepository
import org.example.white.domain.repositories.PreferencesRepository
import org.example.white.domain.use_cases.ConnectToSocketUseCase
import org.example.white.presentation.ParentViewModel
import org.example.white.presentation.chat.ChatListState
import org.example.white.util.Constants

class ChatListViewModel(
    val chatRepository: ChatRepository,
    connectToSocketUseCase: ConnectToSocketUseCase

) : ParentViewModel() {

    private val _state = MutableStateFlow(ChatListState())
    val state = _state.asStateFlow()

    init {
        screenModelScope.launch {
            connectToSocketUseCase.invoke()
        }

    }

    fun fetchMyChatsList() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        screenModelScope.launch {

            val chatList = chatRepository.getUserChats()
            _state.update {
                it.copy(
                    chatsList = chatList.map {
                        it.toChat(it)
                    }.toCollection(ArrayList()),
                    isLoading = false
                )
            }
        }
    }

}