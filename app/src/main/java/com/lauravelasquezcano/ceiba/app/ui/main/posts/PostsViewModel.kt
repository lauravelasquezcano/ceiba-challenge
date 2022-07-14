package com.lauravelasquezcano.ceiba.app.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lauravelasquezcano.ceiba.app.ui.model.GetUserByIdState
import com.lauravelasquezcano.ceiba.app.ui.model.GetUsersState
import com.lauravelasquezcano.ceiba.domain.usecase.GetUserByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {

    private val _getUserByIdState = MutableLiveData<GetUserByIdState>()
    val getUserByIdState: LiveData<GetUserByIdState>
        get() = _getUserByIdState

    fun getUserById(userId: Int) {
        _getUserByIdState.postValue(GetUserByIdState.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserByIdUseCase.execute(userId)
            if (user == null) {
                _getUserByIdState.postValue(GetUserByIdState.Failure)
            } else {
                _getUserByIdState.postValue(GetUserByIdState.Success(user))
            }
        }
    }
}