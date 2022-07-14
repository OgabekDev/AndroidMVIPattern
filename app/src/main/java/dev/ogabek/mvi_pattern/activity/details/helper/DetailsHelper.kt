package dev.ogabek.mvi_pattern.activity.details.helper

import dev.ogabek.mvi_pattern.model.Post

interface DetailsHelper {

    suspend fun detailsPost(id: Int): Post

}