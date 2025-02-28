package com.anand.ktorandroid.network

import android.util.Log
import com.anand.ktorandroid.data.Post
import com.anand.ktorandroid.network.api.PostApi
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class PostsService {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("HTTP Client", message)
                }
            }
        }
    }

    private val postApi = PostApi(client)

    suspend fun getPosts(): List<Post> = postApi.getPosts()

    suspend fun getPostById(id: Int): Post = postApi.getPostById(id)

    fun close() {
        client.close()
    }
}
