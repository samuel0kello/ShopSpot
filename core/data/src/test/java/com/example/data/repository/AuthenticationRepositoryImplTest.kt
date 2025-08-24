package com.example.data.repository

import com.example.data.TestDispatcherRule
import com.example.data.fake.FakeAuthenticationLocalSource
import com.example.data.fake.FakeAuthenticationRemoteSource
import com.samuelokello.core.domain.model.AuthenticatedUser
import com.samuelokello.core.domain.model.LoginResponse
import com.samuelokello.core.domain.model.SessionTokens
import com.samuelokello.core.domain.model.UserCredentials
import com.samuelokello.core.domain.util.Result
import com.samuelokello.data.repository.repository.AuthenticationRepositoryImpl
import com.samuelokello.remote.models.LoginResponseDto
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class AuthenticationRepositoryImplTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    private lateinit var localSource: FakeAuthenticationLocalSource
    private lateinit var remoteSource: FakeAuthenticationRemoteSource
    private lateinit var repository: AuthenticationRepositoryImpl

    @Before
    fun setUp() {
        localSource = FakeAuthenticationLocalSource()
        remoteSource = FakeAuthenticationRemoteSource()
        repository = AuthenticationRepositoryImpl(localSource, remoteSource)
    }

    @After
    fun tearDown() {
        localSource.resetState()
        remoteSource.reset()
    }

    @Test
    fun `getAccessToken returns token from local source`() =
        runTest {
            val expectedToken = "test_access_token"
            localSource.setInitialTokens(expectedToken, "any_refresh")

            val actualToken = repository.getAccessToken().first()

            assertEquals(expectedToken, actualToken)
        }

    @Test
    fun `saveTokens saves tokens to local source`() =
        runTest {
            val accessToken = "new_access_token"
            val refreshToken = "new_refresh_token"

            repository.saveTokens(accessToken, refreshToken)
            runCurrent()

            assertEquals(accessToken, localSource.saveAccessTokenCalledWith)
            assertEquals(refreshToken, localSource.saveRefreshTokenCalledWith)
        }

    @Test
    fun `login success - remote success, saves tokens and returns mapped domain response`() =
        runTest {
            val userCredentials = UserCredentials("samokello", "password")
            val remoteDto =
                LoginResponseDto(
                    id = 1,
                    userName = "samokello",
                    email = "test@example.com",
                    firstName = "Sam",
                    lastName = "Okello",
                    gender = "Male",
                    image = "https//image.com/image.jpg",
                    accessToken = "new_access_token",
                    refreshToken = "new_refresh_token",
                )

            val expectedUser =
                AuthenticatedUser(
                    id = 1,
                    username = "samokello",
                    email = "test@example.com",
                    firstName = "Sam",
                    lastName = "Okello",
                    gender = "Male",
                    profileImageUrl = "https//image.com/image.jpg",
                )
            val expectedTokens =
                SessionTokens(
                    accessToken = "new_access_token",
                    refreshToken = "new_refresh_token",
                )
            val expectedLoginResponse =
                LoginResponse(
                    authenticatedUser = expectedUser,
                    sessionTokens = expectedTokens,
                )

            val job =
                launch {
                    val result = repository.login(userCredentials).first()

                    assertTrue(result is Result.Success)
                    assertEquals(expectedLoginResponse, result.data)
                }

            remoteSource.emitLoginResponse(Result.Success(remoteDto))
            job.join()

            runCurrent()

            assertEquals("new_access_token", localSource.saveAccessTokenCalledWith)
            assertEquals("new_refresh_token", localSource.saveRefreshTokenCalledWith)
            assertNotNull(remoteSource.lastLoginRequest)
            assertNotNull(userCredentials.username, remoteSource.lastLoginRequest?.username)
        }
}