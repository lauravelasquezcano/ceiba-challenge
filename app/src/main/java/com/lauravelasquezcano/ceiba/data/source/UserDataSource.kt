package com.lauravelasquezcano.ceiba.data.source

import com.lauravelasquezcano.ceiba.app.database.User

interface UserDataSource {

    fun isEmpty(): Boolean
    fun insertAll(users: List<User>)
    fun getAllUsers(): List<User>
}