package dev.ogabek.mvi_pattern.activity.details.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ogabek.mvi_pattern.activity.details.intent_state.DetailsIntent
import dev.ogabek.mvi_pattern.activity.details.intent_state.DetailsState
import dev.ogabek.mvi_pattern.activity.update.intent_state.UpdateIntent
import dev.ogabek.mvi_pattern.activity.update.intent_state.UpdateState
import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.repository.DetailsRepository
import dev.ogabek.mvi_pattern.repository.UpdateRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: DetailsRepository) : ViewModel() {

    val detailsIntent = Channel<DetailsIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<DetailsState>(DetailsState.Init)
    val state: StateFlow<DetailsState> get() = _state

    var id: Int = 0

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            detailsIntent.consumeAsFlow().collect {
                when (it) {
                    is DetailsIntent.DetailsPost -> apiDetailsPost()
                }
            }
        }
    }

    private fun apiDetailsPost() {
        viewModelScope.launch {
            _state.value = DetailsState.Loading

            _state.value = try {
                DetailsState.DetailsPost(repository.detailsPost(id))
            } catch (e: Exception) {
                DetailsState.Error(e.localizedMessage)
            }

        }
    }

}