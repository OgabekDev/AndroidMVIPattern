package dev.ogabek.mvi_pattern.repository

import dev.ogabek.mvi_pattern.activity.create.helper.CreateHelper
import dev.ogabek.mvi_pattern.model.Post

class CreateRepository(private val createHelper: CreateHelper) {

    suspend fun createPost(post: Post) = createHelper.createPost(post)

}