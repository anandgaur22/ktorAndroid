package com.anand.ktorandroid.data

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
) 