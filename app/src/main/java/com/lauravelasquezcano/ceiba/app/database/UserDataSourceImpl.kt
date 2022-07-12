package com.lauravelasquezcano.ceiba.app.database

import com.lauravelasquezcano.ceiba.data.source.UserDataSource

class UserDataSourceImpl(private val dao: UserDao): UserDataSource {

    override fun insertAll(users: List<User>): List<Long> = dao.insertAll(users)

    override fun getAllUsers(): List<User> = dao.getAllUsers()
}