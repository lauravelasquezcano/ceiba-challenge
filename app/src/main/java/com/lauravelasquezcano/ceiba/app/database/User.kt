package com.lauravelasquezcano.ceiba.app.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val id: Int,
    val name: String,
    val userName: String,
    val email: String,
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val lat: String,
    val lng: String,
    val phone: String,
    val website: String,
    val companyName: String,
    val catchPhrase: String,
    val bs: String
)
