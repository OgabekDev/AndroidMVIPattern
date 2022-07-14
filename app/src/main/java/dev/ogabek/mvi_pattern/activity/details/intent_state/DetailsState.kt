package dev.ogabek.mvi_pattern.activity.details.intent_state

import dev.ogabek.mvi_pattern.model.Post

sealed class DetailsState {

    object Init: DetailsState()
    object Loading: DetailsState()

    data class DetailsPost(val post: Post): DetailsState()

    data class Error(val error: String?): DetailsState()

}