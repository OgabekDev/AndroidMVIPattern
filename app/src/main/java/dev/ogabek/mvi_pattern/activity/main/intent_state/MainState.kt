package dev.ogabek.mvi_pattern.activity.main.intent_state

import dev.ogabek.mvi_pattern.model.Post

sealed class MainState {

    object Init : MainState()
    object Loading : MainState()

    data class AllPosts(val posts: ArrayList<Post>) : MainState()
    data class DeletePost(val post: Post) : MainState()

    data class Error(val error: String?) : MainState()

}