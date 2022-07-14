package com.lauravelasquezcano.ceiba.app.di.module

import com.lauravelasquezcano.ceiba.app.ui.main.posts.PostsViewModel
import com.lauravelasquezcano.ceiba.app.ui.main.users.UsersViewModel
import com.lauravelasquezcano.ceiba.domain.usecase.GetUserByIdUseCase
import com.lauravelasquezcano.ceiba.domain.usecase.GetUsersByNameUseCase
import com.lauravelasquezcano.ceiba.domain.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
object ViewModelsModule {

    @Provides
    fun providesUserViewModel(getUsersUseCase: GetUsersUseCase, getUsersByNameUseCase: GetUsersByNameUseCase) =
        UsersViewModel(getUsersUseCase, getUsersByNameUseCase)

    @Provides
    fun providesPostsViewModel(getUserByIdUseCase: GetUserByIdUseCase) =
        PostsViewModel(getUserByIdUseCase)
}