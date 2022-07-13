package com.lauravelasquezcano.ceiba.app.database

import com.lauravelasquezcano.ceiba.data.source.PostDataSource

class PostDataSourceImpl(private val dao: PostDao) : PostDataSource {

    override fun insertAll(posts: List<Post>) = dao.insertAll(posts)

    override fun getPostsByUserId(userId: Int): List<Post> = dao.getPostsByUserId(userId)
}