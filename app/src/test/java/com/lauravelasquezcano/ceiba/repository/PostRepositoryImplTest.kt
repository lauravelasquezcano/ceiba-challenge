package com.lauravelasquezcano.ceiba.repository

import com.lauravelasquezcano.ceiba.data.remote.ApiServices
import com.lauravelasquezcano.ceiba.data.repository.PostRepositoryImpl
import com.lauravelasquezcano.ceiba.data.source.PostDataSource
import com.lauravelasquezcano.ceiba.data.source.PostRepository
import com.lauravelasquezcano.ceiba.domain.model.Post
import com.lauravelasquezcano.ceiba.utils.mockedDbPostList
import com.lauravelasquezcano.ceiba.utils.mockedDbUserList
import com.lauravelasquezcano.ceiba.utils.mockedPostsList
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class PostRepositoryImplTest {

    private var apiServices: ApiServices = mockk()
    private var postDataSource: PostDataSource = mockk()
    private lateinit var postRepository: PostRepository

    @Before
    fun setUp() {
        postRepository = PostRepositoryImpl(
            apiServices,
            postDataSource
        )
    }

    @After
    fun tearDown() {
        confirmVerified(apiServices, postDataSource)
    }

    @Test
    fun getPostsByUserEmptyDb() = runBlocking {

        val response: Response<List<Post>> = Response.success(mockedPostsList)

        every { postDataSource.isEmpty(1)} returns true

        coEvery { apiServices.getPostsByUserId("1") } returns response

        every { postDataSource.insertAll(mockedDbPostList) } returns Unit

        every { postDataSource.getPostsByUserId(1) } returns mockedDbPostList

        val result = postRepository.getPosts(1)

        coVerify(exactly = 1) { apiServices.getPostsByUserId("1")}
        verify(exactly = 1) {
            postDataSource.isEmpty(1)
            postDataSource.insertAll(mockedDbPostList)
            postDataSource.getPostsByUserId(1)
        }

        assertEquals(mockedPostsList, result)
    }

    @Test
    fun getPostsByUserNotEmptyDb() = runBlocking {

        every { postDataSource.isEmpty(1)} returns false

        every { postDataSource.getPostsByUserId(1) } returns mockedDbPostList

        val result = postRepository.getPosts(1)

        verify(exactly = 1) {
            postDataSource.isEmpty(1)
            postDataSource.getPostsByUserId(1)
        }

        assertEquals(mockedPostsList, result)
    }
}