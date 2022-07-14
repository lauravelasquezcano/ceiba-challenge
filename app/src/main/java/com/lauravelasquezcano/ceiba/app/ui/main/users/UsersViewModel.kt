package com.lauravelasquezcano.ceiba.app.ui.main.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauravelasquezcano.ceiba.app.ui.model.GetUsersState
import com.lauravelasquezcano.ceiba.domain.usecase.GetUsersByNameUseCase
import com.lauravelasquezcano.ceiba.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersByNameUseCase: GetUsersByNameUseCase
) : ViewModel() {

    private val _getUsersState = MutableLiveData<GetUsersState>()
    val getUsersState: LiveData<GetUsersState>
        get() = _getUsersState

    fun getUsers() {
        _getUsersState.postValue(GetUsersState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val users = getUsersUseCase.execute()
            if (users.isEmpty()) {
                _getUsersState.postValue(GetUsersState.Failure)
            } else {
                _getUsersState.postValue(GetUsersState.Success(users))
            }
        }
    }

    fun getUsersByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val users = getUsersByNameUseCase.execute(name)
            if (users.isEmpty()) {
                _getUsersState.postValue(GetUsersState.EmptySearch)
            } else {
                _getUsersState.postValue(GetUsersState.Success(users))
            }
        }
    }
}