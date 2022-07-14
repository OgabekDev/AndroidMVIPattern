package dev.ogabek.mvi_pattern.activity.main.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.ogabek.mvi_pattern.activity.main.intent_state.MainIntent
import dev.ogabek.mvi_pattern.activity.main.intent_state.MainState
import dev.ogabek.mvi_pattern.repository.PostRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PostRepository): ViewModel() {

    val mainIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<MainState>(MainState.Init)
    val state: StateFlow<MainState> get() = _state

    var postId: Int = 0

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.AllPosts -> apiAllPosts()
                    is MainIntent.DeletePost -> apiDeletePosts()
                }
            }
        }
    }

    private fun apiDeletePosts() {
        viewModelScope.launch {
            _state.value = MainState.Loading

            _state.value = try {
                MainState.AllPosts(repository.getAllPosts())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }

        }
    }

    private fun apiAllPosts() {
        viewModelScope.launch {
            _state.value = MainState.Loading

            _state.value = try {
                MainState.AllPosts(repository.getAllPosts())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }
    }

}