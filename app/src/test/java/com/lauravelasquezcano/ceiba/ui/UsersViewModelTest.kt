package com.lauravelasquezcano.ceiba.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lauravelasquezcano.ceiba.app.ui.main.users.UsersViewModel
import com.lauravelasquezcano.ceiba.app.ui.model.GetUsersState
import com.lauravelasquezcano.ceiba.domain.usecase.GetUsersByNameUseCase
import com.lauravelasquezcano.ceiba.domain.usecase.GetUsersUseCase
import com.lauravelasquezcano.ceiba.utils.mockedUsersList
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

class UsersViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getUsersUseCase: GetUsersUseCase = mockk()
    private val getUsersByNameUseCase: GetUsersByNameUseCase = mockk()

    private lateinit var viewModel: UsersViewModel

    @Before
    fun setUp() {
        viewModel = UsersViewModel(getUsersUseCase, getUsersByNameUseCase)
    }

    @After
    fun tearDown() {
        confirmVerified(getUsersUseCase, getUsersByNameUseCase)
    }

    @Test
    fun getUsersEmptyResponse() {

        coEvery { getUsersUseCase.execute() } returns emptyList()

        viewModel.getUsers()

        coVerify(exactly = 1) { getUsersUseCase.execute() }
        assertEquals(GetUsersState.Failure, viewModel.getUsersState.value)
    }

    @Test
    fun getUsersNotEmptyResponse() {

        coEvery { getUsersUseCase.execute() } returns mockedUsersList

        viewModel.getUsers()

        coVerify(exactly = 1) { getUsersUseCase.execute() }
        assertEquals(GetUsersState.Success(mockedUsersList), viewModel.getUsersState.value)
    }

    @Test
    fun getUsersByNameEmptyResponse() {

        coEvery { getUsersByNameUseCase.execute("nn") } returns emptyList()

        viewModel.getUsersByName("nn")

        coVerify(exactly = 1) { getUsersByNameUseCase.execute("nn") }
        assertEquals(GetUsersState.EmptySearch, viewModel.getUsersState.value)
    }

    @Test
    fun getUsersByNameNotEmptyResponse() {

        coEvery { getUsersByNameUseCase.execute("nn") } returns mockedUsersList

        viewModel.getUsersByName("nn")

        coVerify(exactly = 1) { getUsersByNameUseCase.execute("nn") }
        assertEquals(GetUsersState.Success(mockedUsersList), viewModel.getUsersState.value)
    }
}