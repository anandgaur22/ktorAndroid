package com.anand.ktorandroid.network

import android.util.Log
import com.anand.ktorandroid.data.Post
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
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

    suspend fun getPosts(): List<Post> {
        try {
            return client.get("https://jsonplaceholder.typicode.com/posts").body()
        } catch (e: Exception) {
            Log.e("PostsService", "Error fetching posts", e)
            throw e
        }
    }

    fun close() {
        client.close()
    }
} 