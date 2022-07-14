package com.lauravelasquezcano.ceiba.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {

    @Insert
    fun insertAll(posts: List<Post>)

    @Query("SELECT * FROM post WHERE userId = :userId")
    fun getPostsByUserId(userId: Int): List<Post>

    @Query("SELECT COUNT(id) FROM post")
    fun userCount(): Int
}