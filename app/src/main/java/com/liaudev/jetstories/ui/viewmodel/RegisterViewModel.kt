package com.liaudev.jetstories.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liaudev.jetstories.data.StoryRepository
import com.liaudev.jetstories.data.network.response.BaseResponse
import com.liaudev.jetstories.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

/**
 * Created by Budiman on 15/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
class RegisterViewModel(private val repository: StoryRepository) : ViewModel() {
    private val _uiStateRegister = MutableStateFlow<UiState<BaseResponse>>(UiState.Idle)
    val uiStateRegister: StateFlow<UiState<BaseResponse>> = _uiStateRegister
    fun register(username: String, email: String, password: String) {
        _uiStateRegister.value = UiState.Loading
        viewModelScope.launch {
            repository.register(username, email, password).catch {
                _uiStateRegister.value = UiState.Error(it.message.toString())
            }.collect {
                _uiStateRegister.value = UiState.Success(it)
            }
        }
    }
}