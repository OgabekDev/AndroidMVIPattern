package dev.ogabek.mvi_pattern.activity.main.helper

import dev.ogabek.mvi_pattern.model.Post
import dev.ogabek.mvi_pattern.network.service.PostService

class MainHelperImpl(private val postService: PostService) : MainHelper {
    override suspend fun allPosts(): ArrayList<Post> {
        return postService.allPosts()
    }

    override suspend fun deletePost(id: Int): Post {
        return postService.deletePost(id)
    }


}