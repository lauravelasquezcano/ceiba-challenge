package com.lauravelasquezcano.ceiba.data.source

import com.lauravelasquezcano.ceiba.app.database.User

interface UserDataSource {

    fun insertAll(users: List<User>): List<Long>
    fun getAllUsers(): List<User>
}