package com.lauravelasquezcano.ceiba.app.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Post::class], version = 1, exportSchema = false)
abstract class CeibaDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
}