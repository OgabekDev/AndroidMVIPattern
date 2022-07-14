package dev.ogabek.mvi_pattern.activity.create.intent_state

import dev.ogabek.mvi_pattern.model.Post

sealed class CreateState {

    object Init: CreateState()
    object Loading: CreateState()

    data class CreatePost(val post: Post): CreateState()

    data class Error(val error: String?): CreateState()

}