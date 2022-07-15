package com.lauravelasquezcano.ceiba.usecases

import com.lauravelasquezcano.ceiba.data.source.UserRepository
import com.lauravelasquezcano.ceiba.domain.usecase.GetUsersByNameUseCase
import com.lauravelasquezcano.ceiba.utils.mockedDbUSer
import com.lauravelasquezcano.ceiba.utils.mockedUser
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

class GetUsersByNameUseCaseTest {

    private var userRepository: UserRepository = mockk()
    private lateinit var getUsersByNameUseCase: GetUsersByNameUseCase

    @Before
    fun setUp() {
        getUsersByNameUseCase = GetUsersByNameUseCase(userRepository)
    }

    @After
    fun tearDown() {
        confirmVerified(userRepository)
    }

    @Test
    fun getUsersByName() = runBlocking {

        coEvery { userRepository.getUsersByName("nn") } returns mockedUsersList

        val result = getUsersByNameUseCase.execute("nn")

        assertEquals(mockedUsersList, result)

        coVerify(exactly = 1) { userRepository.getUsersByName("nn")  }
    }
}