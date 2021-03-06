package com.lauravelasquezcano.ceiba.app.database

import com.lauravelasquezcano.ceiba.data.source.UserDataSource

class UserDataSourceImpl(private val dao: UserDao) : UserDataSource {

    override fun isEmpty(): Boolean = dao.userCount() <= 0

    override fun insertAll(users: List<User>) = dao.insertAll(users)

    override fun getAllUsers(): List<User> = dao.getAllUsers()

    override fun getUsersByName(name: String): List<User> = dao.getUsersByName("%$name%")

    override fun getUserById(userId: Int): User? = dao.getUserById(userId)

}