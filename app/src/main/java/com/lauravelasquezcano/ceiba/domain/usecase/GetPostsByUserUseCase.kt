package com.lauravelasquezcano.ceiba.domain.usecase

import com.lauravelasquezcano.ceiba.data.source.PostRepository
import javax.inject.Inject

class GetPostsByUserUseCase @Inject constructor(
    private val repository: PostRepository
) {
    suspend fun execute(userId: Int) = repository.getPosts(userId)
}