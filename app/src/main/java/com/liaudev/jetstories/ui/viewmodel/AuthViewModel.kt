package com.liaudev.jetstories.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.liaudev.jetstories.data.StoryRepository
import com.liaudev.jetstories.data.network.response.LoginResponse
import com.liaudev.jetstories.data.network.response.LoginResult
import com.liaudev.jetstories.model.User
import com.liaudev.jetstories.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

/**
 * Created by Budiman on 14/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
class AuthViewModel(private val repository: StoryRepository) : ViewModel() {
    private val _uiStateLogin = MutableStateFlow<UiState<LoginResponse>>(UiState.Idle)
    val uiStateLogin: StateFlow<UiState<LoginResponse>> = _uiStateLogin
    fun login(email: String, password: String) {
        _uiStateLogin.value = UiState.Loading
        viewModelScope.launch {
            repository.login(email, password).catch {
                _uiStateLogin.value = UiState.Error(it.message.toString())
            }.collect {
                val user = User(
                    it.loginResult?.userId.toString(),
                    it.loginResult?.name.toString(),
                    it.loginResult?.token.toString(),
                    true
                )
                repository.saveUser(user)
                _uiStateLogin.value = UiState.Success(it)
            }
        }
    }
    fun logOut() {
        viewModelScope.launch {
            val user = User("", "", "", false)
            repository.saveUser(user)
        }
    }

    fun getUser(): LiveData<User> = repository.getUserData()

    /*
    fun testerFun() {
        _uiStateLogin.value = UiState.Loading
        Timer().schedule(1000L) {
            val loginResult = LoginResult("userid", "budiliauw87", "")
            val response = LoginResponse(false, "Successfully login", null)
            _uiStateLogin.value = UiState.Success(response)
        }
    }*/
}