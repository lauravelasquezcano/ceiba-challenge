package com.lauravelasquezcano.ceiba.app.database

import androidx.room.Database

@Database(entities = [User::class, Post::class], version = 1, exportSchema = false)
abstract class CeibaDatabase {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
}