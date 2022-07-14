package dev.ogabek.mvi_pattern.repository

import dev.ogabek.mvi_pattern.activity.details.helper.DetailsHelper

class DetailsRepository(private val detailsHelper: DetailsHelper) {

    suspend fun detailsPost(id: Int) = detailsHelper.detailsPost(id)

}