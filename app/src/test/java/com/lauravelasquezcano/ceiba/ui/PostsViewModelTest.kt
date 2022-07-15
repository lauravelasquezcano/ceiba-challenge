package com.lauravelasquezcano.ceiba.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lauravelasquezcano.ceiba.app.ui.main.posts.PostsViewModel
import com.lauravelasquezcano.ceiba.app.ui.model.GetPostsByUserState
import com.lauravelasquezcano.ceiba.app.ui.model.GetUserByIdState
import com.lauravelasquezcano.ceiba.domain.usecase.GetPostsByUserUseCase
import com.lauravelasquezcano.ceiba.domain.usecase.GetUserByIdUseCase
import com.lauravelasquezcano.ceiba.utils.mockedPostsList
import com.lauravelasquezcano.ceiba.utils.mockedUser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class PostsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getUserByIdUseCase: GetUserByIdUseCase = mockk()
    private val getPostsByUserUseCase: GetPostsByUserUseCase = mockk()

    private lateinit var viewModel: PostsViewModel

    @Before
    fun setUp() {
        viewModel = PostsViewModel(getUserByIdUseCase, getPostsByUserUseCase)
    }

    @After
    fun tearDown() {
        confirmVerified(getUserByIdUseCase, getPostsByUserUseCase)
    }

    @Test
    fun getUserByIdNullResponse() {

        coEvery { getUserByIdUseCase.execute(1) } returns null

        viewModel.getUserById(1)

        coVerify(exactly = 1) { getUserByIdUseCase.execute(1) }
        assertEquals(GetUserByIdState.Failure, viewModel.getUserByIdState.value)
    }

    @Test
    fun getUserByIdNotNullResponse() {

        coEvery { getUserByIdUseCase.execute(1) } returns mockedUser

        viewModel.getUserById(1)

        coVerify(exactly = 1) { getUserByIdUseCase.execute(1) }
        assertEquals(GetUserByIdState.Success(mockedUser), viewModel.getUserByIdState.value)
    }

    @Test
    fun getPostsByUserEmptyResponse() {

        coEvery { getPostsByUserUseCase.execute(1) } returns emptyList()

        viewModel.getPostsByUser(1)

        coVerify(exactly = 1) { getPostsByUserUseCase.execute(1) }
        assertEquals(GetPostsByUserState.Failure, viewModel.getPostsByUserState.value)
    }

    @Test
    fun getPostsByUserNotEmptyResponse() {

        coEvery { getPostsByUserUseCase.execute(1) } returns mockedPostsList

        viewModel.getPostsByUser(1)

        coVerify(exactly = 1) { getPostsByUserUseCase.execute(1) }
        assertEquals(GetPostsByUserState.Success(mockedPostsList), viewModel.getPostsByUserState.value)
    }
}