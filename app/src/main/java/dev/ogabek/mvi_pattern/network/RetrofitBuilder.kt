package dev.ogabek.mvi_pattern.network

import dev.ogabek.mvi_pattern.network.service.PostService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitBuilder {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val POST_SERVICE: PostService = getRetrofit().create(PostService::class.java)

}