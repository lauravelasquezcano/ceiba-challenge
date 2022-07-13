package com.lauravelasquezcano.ceiba.data.source

import com.lauravelasquezcano.ceiba.domain.User
import com.lauravelasquezcano.ceiba.app.database.User as DbUser

interface UserRepository {

    suspend fun getUsers() : List<User>

    suspend fun saveUsers(users: List<DbUser>)
}