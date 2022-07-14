package com.lauravelasquezcano.ceiba.domain.usecase

import com.lauravelasquezcano.ceiba.data.source.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val repository: UserRepository
) {

    suspend fun execute(userId: Int) = repository.getUserById(userId)
}