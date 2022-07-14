package com.lauravelasquezcano.ceiba.data.remote

import com.lauravelasquezcano.ceiba.domain.model.PostResponse
import com.lauravelasquezcano.ceiba.domain.model.User
import com.lauravelasquezcano.ceiba.domain.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("/users")
    suspend fun getAllUsers(): Response<List<User>>

    @GET("/posts")
    suspend fun getPostsByUserId(
        @Query("userId") userId: String
    ): Response<PostResponse>
}