package com.lauravelasquezcano.ceiba.app.ui.model

import com.lauravelasquezcano.ceiba.domain.model.User

sealed class GetUserByIdState {

    object Loading : GetUserByIdState()
    data class Success(val user : User) : GetUserByIdState()
    object Failure : GetUserByIdState()
}