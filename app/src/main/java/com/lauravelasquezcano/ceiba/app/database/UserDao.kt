package com.lauravelasquezcano.ceiba.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT COUNT(id) FROM user")
    fun userCount(): Int

    @Query("SELECT * FROM user WHERE name LIKE :name")
    fun getUsersByName(name: String): List<User>
}