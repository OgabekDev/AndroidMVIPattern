package dev.ogabek.mvi_pattern.activity.main.intent_state

sealed class MainIntent {

    object AllPosts: MainIntent()
    object DeletePost: MainIntent()

}