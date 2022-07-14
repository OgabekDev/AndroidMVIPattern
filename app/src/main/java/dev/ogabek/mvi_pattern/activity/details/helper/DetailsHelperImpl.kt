package dev.ogabek.mvi_pattern.activity.details.helper

import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.network.service.PostService

class DetailsHelperImpl(private val postService: PostService) : DetailsHelper {

    override suspend fun detailsPost(id: Int): Post {
        return postService.getPosts(id)
    }

}