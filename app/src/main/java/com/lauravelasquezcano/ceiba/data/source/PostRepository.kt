package com.lauravelasquezcano.ceiba.data.source

import com.lauravelasquezcano.ceiba.domain.Post
import com.lauravelasquezcano.ceiba.app.database.Post as DbPost

interface PostRepository {

    fun getPosts(userId: Int) : List<Post>

    fun savePosts(posts: List<DbPost>)
}