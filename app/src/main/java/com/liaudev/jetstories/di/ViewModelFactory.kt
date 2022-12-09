package com.liaudev.jetstories.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liaudev.jetstories.data.StoryRepository
import com.liaudev.jetstories.data.network.ApiConfig
import com.liaudev.jetstories.ui.viewmodel.StoryViewModel

/**
 * Created by Budiman on 09/12/2022.
 * Email budiliauw87@gmail.com
 * Github https://github.com/budiliauw87
 */
class ViewModelFactory(private val repo: StoryRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoryViewModel::class.java)) {
            return StoryViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injector.provideRepository())
            }.also { INSTANCE = it }
        }
    }
}


object Injector {
    fun provideRepository(): StoryRepository {
        val apiService = ApiConfig.provideApiService()
        return StoryRepository.getInstance(apiService)
    }
}