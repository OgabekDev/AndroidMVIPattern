package dev.ogabek.mvi_pattern.activity.create.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ogabek.mvi_pattern.activity.create.intent_state.CreateIntent
import dev.ogabek.mvi_pattern.activity.create.intent_state.CreateState
import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.repository.CreateRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CreateViewModel(private val repository: CreateRepository): ViewModel() {

    val createIntent = Channel<CreateIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<CreateState>(CreateState.Init)
    val state: StateFlow<CreateState> get() = _state

    lateinit var post: Post

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            createIntent.consumeAsFlow().collect {
                when (it) {
                    is CreateIntent.CreatePost -> apiCreatePost()
                }
            }
        }
    }

    private fun apiCreatePost() {
        viewModelScope.launch {
            _state.value = CreateState.Loading

            _state.value = try {
                CreateState.CreatePost(repository.createPost(post))
            } catch (e: Exception) {
                CreateState.Error(e.localizedMessage)
            }

        }
    }

}