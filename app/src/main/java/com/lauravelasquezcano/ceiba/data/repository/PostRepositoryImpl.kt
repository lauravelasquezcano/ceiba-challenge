package com.lauravelasquezcano.ceiba.data.repository

import com.lauravelasquezcano.ceiba.data.source.PostRepository
import com.lauravelasquezcano.ceiba.domain.Post

class PostRepositoryImpl : PostRepository {

    override fun getPosts(userId: Int): List<Post> {
        TODO("Not yet implemented")
    }

    override fun savePosts(posts: List<com.lauravelasquezcano.ceiba.app.database.Post>) {
        TODO("Not yet implemented")
    }
}