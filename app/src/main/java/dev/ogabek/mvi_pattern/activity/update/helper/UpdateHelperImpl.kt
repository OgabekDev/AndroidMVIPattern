package dev.ogabek.mvi_pattern.activity.update.helper

import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.network.service.PostService

class UpdateHelperImpl(private val postService: PostService) : UpdateHelper {

    override suspend fun updatePost(id: Int, post: Post): Post {
        return postService.updatePost(id, post)
    }

}