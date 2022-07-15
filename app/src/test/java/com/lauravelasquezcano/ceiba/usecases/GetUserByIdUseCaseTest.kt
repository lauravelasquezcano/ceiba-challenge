package com.lauravelasquezcano.ceiba.usecases

import com.lauravelasquezcano.ceiba.data.source.UserRepository
import com.lauravelasquezcano.ceiba.domain.usecase.GetUserByIdUseCase
import com.lauravelasquezcano.ceiba.utils.mockedUser
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetUserByIdUseCaseTest {

    private var userRepository: UserRepository = mockk()
    private lateinit var getUserByIdUseCase: GetUserByIdUseCase

    @Before
    fun setUp() {
        getUserByIdUseCase = GetUserByIdUseCase(userRepository)
    }

    @After
    fun tearDown() {
        confirmVerified(userRepository)
    }

    @Test
    fun getUserById() = runBlocking {

        coEvery { userRepository.getUserById(1) } returns mockedUser

        val result = getUserByIdUseCase.execute(1)

        assertEquals(mockedUser, result)

        coVerify(exactly = 1) { userRepository.getUserById(1) }
    }
}