package dev.ogabek.mvi_pattern.activity.main.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ogabek.mvi_pattern.activity.main.helper.MainHelper
import dev.ogabek.mvi_pattern.repository.PostRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val mainHelper: MainHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(PostRepository(mainHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}