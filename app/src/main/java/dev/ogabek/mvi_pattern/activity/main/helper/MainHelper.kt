package dev.ogabek.mvi_pattern.activity.main.helper

import dev.ogabek.mvi_pattern.model.Post

interface MainHelper {

    suspend fun allPosts(): ArrayList<Post>

    suspend fun deletePost(id: Int): Post

}