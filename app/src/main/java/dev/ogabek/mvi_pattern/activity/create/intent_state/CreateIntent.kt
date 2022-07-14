package dev.ogabek.mvi_pattern.activity.create.intent_state

sealed class CreateIntent {

    object CreatePost : CreateIntent()

}