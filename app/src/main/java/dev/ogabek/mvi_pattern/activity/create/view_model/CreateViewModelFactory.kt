package dev.ogabek.mvi_pattern.activity.create.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ogabek.mvi_pattern.activity.create.helper.CreateHelper
import dev.ogabek.mvi_pattern.activity.create.view_model.CreateViewModel
import dev.ogabek.mvi_pattern.repository.CreateRepository

class CreateViewModelFactory(private val createHelper: CreateHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateViewModel::class.java)) {
            return CreateViewModel(CreateRepository(createHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}