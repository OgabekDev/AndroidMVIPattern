package dev.ogabek.mvi_pattern.repository

import dev.ogabek.mvi_pattern.activity.update.helper.UpdateHelper
import dev.ogabek.mvi_pattern.model.Post

class UpdateRepository(private val updateHelper: UpdateHelper) {

    suspend fun updatePost(id: Int, post: Post) = updateHelper.updatePost(id, post)

}