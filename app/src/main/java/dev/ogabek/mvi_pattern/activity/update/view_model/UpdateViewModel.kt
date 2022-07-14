package dev.ogabek.mvi_pattern.activity.update.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ogabek.mvi_pattern.activity.update.intent_state.UpdateIntent
import dev.ogabek.mvi_pattern.activity.update.intent_state.UpdateState
import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.repository.UpdateRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class UpdateViewModel(private val repository: UpdateRepository) : ViewModel() {

    val updateIntent = Channel<UpdateIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<UpdateState>(UpdateState.Init)
    val state: StateFlow<UpdateState> get() = _state

    var id: Int = 0
    lateinit var post: Post

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            updateIntent.consumeAsFlow().collect {
                when (it) {
                    is UpdateIntent.UpdatePost -> apiUpdatePost()
                }
            }
        }
    }

    private fun apiUpdatePost() {
        viewModelScope.launch {
            _state.value = UpdateState.Loading

            _state.value = try {
                UpdateState.UpdatePost(repository.updatePost(id, post))
            } catch (e: Exception) {
                UpdateState.Error(e.localizedMessage)
            }

        }
    }

}