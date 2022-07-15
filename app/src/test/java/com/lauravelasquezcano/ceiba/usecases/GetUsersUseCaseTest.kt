package com.lauravelasquezcano.ceiba.usecases

import com.lauravelasquezcano.ceiba.data.source.UserRepository
import com.lauravelasquezcano.ceiba.domain.usecase.GetUsersUseCase
import com.lauravelasquezcano.ceiba.utils.mockedUsersList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {

    private var userRepository: UserRepository = mockk()
    private lateinit var getUsersUseCase: GetUsersUseCase

    @Before
    fun setUp() {
        getUsersUseCase = GetUsersUseCase(userRepository)
    }

    @After
    fun tearDown() {
        confirmVerified(userRepository)
    }

    @Test
    fun getUsers() = runBlocking {

        coEvery { userRepository.getUsers() } returns mockedUsersList

        val result = getUsersUseCase.execute()

        assertEquals(mockedUsersList, result)

        coVerify(exactly = 1) { userRepository.getUsers() }
    }
}