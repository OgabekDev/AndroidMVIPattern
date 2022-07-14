package dev.ogabek.mvi_pattern.activity.details.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.ogabek.mvi_pattern.activity.details.helper.DetailsHelper
import dev.ogabek.mvi_pattern.activity.update.helper.UpdateHelper
import dev.ogabek.mvi_pattern.repository.DetailsRepository
import dev.ogabek.mvi_pattern.repository.UpdateRepository

class DetailsViewModelFactory(private val detailsHelper: DetailsHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(DetailsRepository(detailsHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}