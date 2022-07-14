package com.lauravelasquezcano.ceiba.app.ui.model

import com.lauravelasquezcano.ceiba.domain.model.Post

sealed class GetPostsByUserState {
    data class Success(val posts: List<Post>) : GetPostsByUserState()
    object Failure : GetPostsByUserState()
}
