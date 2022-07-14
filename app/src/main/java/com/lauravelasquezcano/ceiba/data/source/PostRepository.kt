package com.lauravelasquezcano.ceiba.data.source

import com.lauravelasquezcano.ceiba.domain.model.Post

interface PostRepository {

    suspend fun getPosts(userId: Int) : List<Post>
}