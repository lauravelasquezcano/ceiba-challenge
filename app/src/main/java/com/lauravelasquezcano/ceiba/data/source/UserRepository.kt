package com.lauravelasquezcano.ceiba.data.source

import com.lauravelasquezcano.ceiba.domain.model.User

interface UserRepository {

    suspend fun getUsers() : List<User>

    suspend fun getUsersByName(name: String) : List<User>

    suspend fun getUserById(userId: Int) : User?
}