package com.liaudev.jetstories.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.liaudev.jetstories.data.StoryRepository
import com.liaudev.jetstories.model.User

/**
 * Created by Budiman on 15/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
class SplashViewModel(private val repository: StoryRepository) : ViewModel() {
    fun getUser(): LiveData<User> = repository.getUserData()
}