package com.lauravelasquezcano.ceiba.app.di.module

import com.lauravelasquezcano.ceiba.data.repository.PostRepositoryImpl
import com.lauravelasquezcano.ceiba.data.repository.UserRepositoryImpl
import com.lauravelasquezcano.ceiba.data.source.PostRepository
import com.lauravelasquezcano.ceiba.data.source.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository
}