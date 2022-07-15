package com.lauravelasquezcano.ceiba.usecases

import com.lauravelasquezcano.ceiba.data.source.PostRepository
import com.lauravelasquezcano.ceiba.domain.usecase.GetPostsByUserUseCase
import com.lauravelasquezcano.ceiba.utils.mockedPostsList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetPostsByUserUseCaseTest {

    private var postRepository: PostRepository = mockk()
    private lateinit var getPostsByUserUseCase: GetPostsByUserUseCase

    @Before
    fun setUp() {
        getPostsByUserUseCase = GetPostsByUserUseCase(postRepository)
    }

    @After
    fun tearDown() {
        confirmVerified(postRepository)
    }

    @Test
    fun getPosts() = runBlocking {

        coEvery { postRepository.getPosts(1) } returns mockedPostsList

        val result = getPostsByUserUseCase.execute(1)

        assertEquals(mockedPostsList, result)

        coVerify(exactly = 1) { postRepository.getPosts(1) }
    }
}