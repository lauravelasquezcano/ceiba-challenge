package com.lauravelasquezcano.ceiba.data.source

import com.lauravelasquezcano.ceiba.app.database.Post

interface PostDataSource {

    fun insertAll(posts: List<Post>)
    fun getPostsByUserId(userId: Int): List<Post>
}