package com.lauravelasquezcano.ceiba.app.ui.model

import com.lauravelasquezcano.ceiba.domain.model.User


sealed class GetUsersState {
    object Loading : GetUsersState()
    data class Success(val users: List<User>) : GetUsersState()
    object Failure : GetUsersState()
    object EmptySearch : GetUsersState()
}
