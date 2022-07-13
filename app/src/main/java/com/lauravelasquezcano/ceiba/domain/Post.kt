package com.lauravelasquezcano.ceiba.domain

data class Post(
    val id: String,
    val userId: Int,
    val title: String,
    val body: String
)
