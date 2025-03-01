package com.anand.ktorandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anand.ktorandroid.data.Post
import com.anand.ktorandroid.network.PostsService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val postsService = PostsService()
    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val posts: List<Post> = postsService.getPosts()
                _uiState.value = UiState.Success(posts)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load posts")
            }
        }
    }

    sealed class UiState {
        data object Initial : UiState()
        data object Loading : UiState()
        data class Success(val posts: List<Post>) : UiState()
        data class Error(val message: String) : UiState()
    }

    override fun onCleared() {
        super.onCleared()
        postsService.close()
    }
} 