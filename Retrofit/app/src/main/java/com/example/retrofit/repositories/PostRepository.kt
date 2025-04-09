package com.example.retrofit.repositories

import com.example.retrofit.api.JsonPlaceHolderService
import com.example.retrofit.api.dto.Comments
import com.example.retrofit.api.dto.Post
import com.example.retrofit.api.dto.Posts

object PostRepository {
    private val service by lazy {
        RetrofitRepository.getRetrofitInstance()
            .create(JsonPlaceHolderService::class.java)
    }

    suspend fun getAllPosts(): Posts = service.getPosts()

    suspend fun getPostDetail(postId: Int): Pair<Post, Comments> {
        return try {
            val post = service.getPostById(postId)
            val comments = service.getCommentsByPostId(postId)
            post to comments
        } catch (e: Exception) {
            throw e
        }
    }
}