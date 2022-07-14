package dev.ogabek.mvi_pattern.activity.create.helper

import dev.ogabek.mvi_pattern.model.Post

interface CreateHelper {

    suspend fun createPost(post: Post): Post

}