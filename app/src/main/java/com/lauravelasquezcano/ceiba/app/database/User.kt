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
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)

data class Geo(
    val lat: String,
    val long: String
)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
