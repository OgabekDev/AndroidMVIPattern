package dev.ogabek.mvi_pattern.network.service

import dev.ogabek.mvi_pattern.model.Post
import retrofit2.http.*

interface PostService {

    @GET("posts")
    suspend fun allPosts(): ArrayList<Post>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Post

    @Headers("Content-type: application/json; charset=UTF-8")
    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

    @Headers("Content-type: application/json; charset=UTF-8")
    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: Post): Post

    @GET("posts/{id}")
    suspend fun getPosts(@Path("id") id: Int): Post

}