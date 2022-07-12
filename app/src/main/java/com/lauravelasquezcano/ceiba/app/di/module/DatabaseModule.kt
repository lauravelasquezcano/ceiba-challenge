package com.lauravelasquezcano.ceiba.app.di.module

import android.app.Application
import androidx.room.Room
import com.lauravelasquezcano.ceiba.app.database.CeibaDatabase
import com.lauravelasquezcano.ceiba.app.database.PostDataSourceImpl
import com.lauravelasquezcano.ceiba.app.database.UserDataSourceImpl
import com.lauravelasquezcano.ceiba.data.source.PostDataSource
import com.lauravelasquezcano.ceiba.data.source.UserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun userDataSourceProvider(db: CeibaDatabase): UserDataSource = UserDataSourceImpl(db.userDao())

    @Singleton
    @Provides
    fun postDataSourceProvider(db: CeibaDatabase): PostDataSource = PostDataSourceImpl(db.postDao())

    @Singleton
    @Provides
    fun databaseProvider(app: Application) =
        Room.databaseBuilder(app, CeibaDatabase::class.java, "Ceiba.db").build()

}