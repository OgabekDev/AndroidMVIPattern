package dev.ogabek.mvi_pattern.model

data class Post(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)