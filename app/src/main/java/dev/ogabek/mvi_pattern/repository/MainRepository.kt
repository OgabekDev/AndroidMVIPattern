package dev.ogabek.mvi_pattern.repository

import dev.ogabek.mvi_pattern.activity.main.helper.MainHelper

class MainRepository(private val mainHelper: MainHelper) {

    suspend fun getAllPosts() = mainHelper.allPosts()

    suspend fun deletePost(id: Int) = mainHelper.deletePost(id)

}