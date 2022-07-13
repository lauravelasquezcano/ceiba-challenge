package com.lauravelasquezcano.ceiba.data.repository

import com.lauravelasquezcano.ceiba.data.source.UserRepository
import com.lauravelasquezcano.ceiba.domain.User

class UserRepositoryImpl : UserRepository {

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun saveUsers(users: List<com.lauravelasquezcano.ceiba.app.database.User>) {
        TODO("Not yet implemented")
    }
}