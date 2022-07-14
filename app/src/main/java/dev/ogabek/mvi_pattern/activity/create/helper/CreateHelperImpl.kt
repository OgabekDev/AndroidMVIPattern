package dev.ogabek.mvi_pattern.activity.create.helper

import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.network.service.PostService

class CreateHelperImpl(private val postService: PostService) : CreateHelper {

    override suspend fun createPost(post: Post): Post {
        return postService.createPost(post)
    }

}