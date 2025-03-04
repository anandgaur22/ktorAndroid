package com.anand.ktorandroid.network.api

import com.anand.ktorandroid.data.Post
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class PostApi(
    private val client: HttpClient,
    private val baseUrl: String = "https://jsonplaceholder.typicode.com"
) {
    suspend fun getPosts(): List<Post> {
        return client.get("$baseUrl/posts").body()
    }

    suspend fun getPostById(id: Int): Post {
        return client.get("$baseUrl/posts/$id").body()
    }


    /*suspend fun sendProposal(userId: String, proposalId: String, status: String): ProposalResponse? {
        return try {
            client.post("$baseUrl/api/general_api/proposalList") {
                contentType(ContentType.Application.FormUrlEncoded)
                setBody(
                    Parameters.build {
                        append("user_id", userId)
                        append("proposal_id", proposalId)
                        append("status", status)
                    }
                )
            }.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }*/
} 