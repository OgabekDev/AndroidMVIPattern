package dev.ogabek.mvi_pattern.activity.update.intent_state

sealed class UpdateIntent {

    object UpdatePost: UpdateIntent()

}