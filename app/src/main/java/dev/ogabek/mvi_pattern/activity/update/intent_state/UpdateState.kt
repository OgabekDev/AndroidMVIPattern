package dev.ogabek.mvi_pattern.activity.update.intent_state

import dev.ogabek.mvi_pattern.model.Post

sealed class UpdateState {

    object Init: UpdateState()
    object Loading: UpdateState()

    data class UpdatePost(val post: Post): UpdateState()

    data class Error(val error: String?): UpdateState()

}