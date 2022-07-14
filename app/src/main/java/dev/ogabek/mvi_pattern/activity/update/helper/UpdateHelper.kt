package dev.ogabek.mvi_pattern.activity.update.helper

import dev.ogabek.mvi_pattern.model.Post

interface UpdateHelper {

    suspend fun updatePost(id: Int, post: Post): Post

}