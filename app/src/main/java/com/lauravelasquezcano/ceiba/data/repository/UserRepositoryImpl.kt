package com.lauravelasquezcano.ceiba.data.repository

import com.lauravelasquezcano.ceiba.app.mappers.toDbUser
import com.lauravelasquezcano.ceiba.app.mappers.toUser
import com.lauravelasquezcano.ceiba.data.remote.ApiServices
import com.lauravelasquezcano.ceiba.data.source.UserDataSource
import com.lauravelasquezcano.ceiba.data.source.UserRepository
import com.lauravelasquezcano.ceiba.domain.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiServices: ApiServices,
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        if (userDataSource.isEmpty()) {
            val users = apiServices.getAllUsers().body()
            userDataSource.insertAll(users!!.map { it.toDbUser() })
        }
        return userDataSource.getAllUsers().map { it.toUser() }
    }

    override suspend fun getUsersByName(name: String): List<User> =
        userDataSource.getUsersByName(name).map { it.toUser() }
}