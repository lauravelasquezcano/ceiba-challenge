package com.lauravelasquezcano.ceiba.data.source

import com.lauravelasquezcano.ceiba.app.database.Post

interface PostDataSource {

    fun isEmpty(userId: Int): Boolean
    fun insertAll(posts: List<Post>)
    fun getPostsByUserId(userId: Int): List<Post>
}