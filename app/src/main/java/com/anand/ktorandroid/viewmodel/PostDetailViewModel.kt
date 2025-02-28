package com.anand.ktorandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anand.ktorandroid.data.Post
import com.anand.ktorandroid.network.PostsService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostDetailViewModel : ViewModel() {
    private val postsService = PostsService()
    private val _uiState = MutableStateFlow<UiState>(UiState.Initial)
    val uiState: StateFlow<UiState> = _uiState

    fun loadPost(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val post: Post = postsService.getPostById(id)
                _uiState.value = UiState.Success(post)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Failed to load post")
            }
        }
    }

    sealed class UiState {
        data object Initial : UiState()
        data object Loading : UiState()
        data class Success(val post: Post) : UiState()
        data class Error(val message: String) : UiState()
    }

    override fun onCleared() {
        super.onCleared()
        postsService.close()
    }
} 