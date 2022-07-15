package com.lauravelasquezcano.ceiba.repository

import com.lauravelasquezcano.ceiba.data.remote.ApiServices
import com.lauravelasquezcano.ceiba.data.repository.UserRepositoryImpl
import com.lauravelasquezcano.ceiba.data.source.UserDataSource
import com.lauravelasquezcano.ceiba.data.source.UserRepository
import com.lauravelasquezcano.ceiba.domain.model.User
import com.lauravelasquezcano.ceiba.utils.mockedDbUSer
import com.lauravelasquezcano.ceiba.utils.mockedDbUserList
import com.lauravelasquezcano.ceiba.utils.mockedUser
import com.lauravelasquezcano.ceiba.utils.mockedUsersList
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UserRepositoryImplTest {

    private var apiServices: ApiServices = mockk()
    private var userDataSource: UserDataSource = mockk()
    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = UserRepositoryImpl(
            apiServices,
            userDataSource
        )
    }

    @After
    fun tearDown() {
        confirmVerified(apiServices, userDataSource)
    }

    @Test
    fun getUsersEmptyDb() = runBlocking {

        val response: Response<List<User>> = Response.success(mockedUsersList)

        every { userDataSource.isEmpty() } returns true

        coEvery { apiServices.getAllUsers() } returns response

        every { userDataSource.insertAll(mockedDbUserList) } returns Unit

        every { userDataSource.getAllUsers() } returns mockedDbUserList

        val result = userRepository.getUsers()

        coVerify(exactly = 1) { apiServices.getAllUsers() }
        verify(exactly = 1) {
            userDataSource.isEmpty()
            userDataSource.insertAll(mockedDbUserList)
            userDataSource.getAllUsers()
        }

        assertEquals(mockedUsersList, result)
    }

    @Test
    fun getUsersNotEmptyDb() = runBlocking {

        every { userDataSource.isEmpty() } returns false

        every { userDataSource.getAllUsers() } returns mockedDbUserList

        val result = userRepository.getUsers()

        verify(exactly = 1) {
            userDataSource.isEmpty()
            userDataSource.getAllUsers()
        }

        assertEquals(mockedUsersList, result)
    }

    @Test
    fun getUsersByName() = runBlocking {

        every { userDataSource.getUsersByName("nn") } returns mockedDbUserList

        val result = userRepository.getUsersByName("nn")

        verify(exactly = 1) { userDataSource.getUsersByName("nn") }
        assertEquals(mockedUsersList, result)
    }

    @Test
    fun getUserById() = runBlocking {

        every { userDataSource.getUserById(1) } returns mockedDbUSer

        val result = userRepository.getUserById(1)

        verify(exactly = 1) { userDataSource.getUserById(1) }
        assertEquals(mockedUser, result)

    }
}