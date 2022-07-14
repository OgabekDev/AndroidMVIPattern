package dev.ogabek.mvi_pattern.activity.update.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ogabek.mvi_pattern.activity.update.helper.UpdateHelper
import dev.ogabek.mvi_pattern.repository.UpdateRepository

class UpdateViewModelFactory(private val updateHelper: UpdateHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UpdateViewModel::class.java)) {
            return UpdateViewModel(UpdateRepository(updateHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}