package dev.ogabek.mvi_pattern.activity.main.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ogabek.mvi_pattern.activity.main.helper.MainHelper
import dev.ogabek.mvi_pattern.repository.MainRepository

class MainViewModelFactory(private val mainHelper: MainHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(mainHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}