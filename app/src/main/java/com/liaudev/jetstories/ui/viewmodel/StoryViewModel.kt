package com.liaudev.jetstories.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.liaudev.jetstories.data.StoryRepository
import com.liaudev.jetstories.model.User
import kotlinx.coroutines.launch

/**
 * Created by Budiman on 09/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
class StoryViewModel(private val repository: StoryRepository) : ViewModel() {
    val storiesPager = repository.getStories().cachedIn(viewModelScope)
    fun logOut() {
        viewModelScope.launch {
            val user = User("", "", "", false)
            repository.saveUser(user)

        }
    }
}