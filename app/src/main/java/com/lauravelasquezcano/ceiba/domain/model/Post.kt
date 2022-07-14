package com.lauravelasquezcano.ceiba.domain.model

data class Post(
    val id: String,
    val userId: Int,
    val title: String,
    val body: String
)
