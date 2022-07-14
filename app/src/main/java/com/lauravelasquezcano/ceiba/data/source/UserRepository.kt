package com.lauravelasquezcano.ceiba.data.source

import com.lauravelasquezcano.ceiba.domain.model.User

interface UserRepository {

    suspend fun getUsers() : List<User>
}