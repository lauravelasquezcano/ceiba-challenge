package com.lauravelasquezcano.ceiba.data.repository

import com.lauravelasquezcano.ceiba.app.mappers.toDbPost
import com.lauravelasquezcano.ceiba.app.mappers.toPost
import com.lauravelasquezcano.ceiba.data.remote.ApiServices
import com.lauravelasquezcano.ceiba.data.source.PostDataSource
import com.lauravelasquezcano.ceiba.data.source.PostRepository
import com.lauravelasquezcano.ceiba.domain.model.Post
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val postDataSource: PostDataSource
) : PostRepository {

    override suspend fun getPosts(userId: Int): List<Post> {
        if (postDataSource.isEmpty(userId)) {
            val posts = apiServices.getPostsByUserId(userId.toString()).body()
            postDataSource.insertAll(posts!!.map { it.toDbPost() })
        }
        return postDataSource.getPostsByUserId(userId).map { it.toPost() }
    }
}