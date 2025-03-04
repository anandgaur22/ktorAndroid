package com.anand.ktorandroid.data

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)

/*
@Serializable
data class ProposalRequest(
    val user_id: String,
    val proposal_id: String,
    val status: String
)

@Serializable
data class ProposalResponse(
    val success: Boolean,
    val message: String
)
*/
