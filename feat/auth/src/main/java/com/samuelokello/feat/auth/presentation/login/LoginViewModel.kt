package com.samuelokello.feat.auth.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelokello.core.domain.model.LoginResponse
import com.samuelokello.core.domain.model.UserCredentials
import com.samuelokello.core.domain.repository.AuthenticationRepository
import com.samuelokello.core.domain.util.DataError
import com.samuelokello.core.domain.util.Result
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthenticationRepository,
) : ViewModel() {
    private val _uiState = mutableStateOf(LoginUiState())
    val uiState: State<LoginUiState> = _uiState

    private val _navigationEvent = MutableSharedFlow<String>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UsernameChanged -> {
                _uiState.value =
                    _uiState.value.copy(
                        username = event.value,
                        usernameError = validateUsername(event.value),
                    )
            }
            is LoginEvent.PasswordChanged -> {
                _uiState.value =
                    _uiState.value.copy(
                        password = event.value,
                        passwordError = validatePassword(event.value),
                    )
            }
            is LoginEvent.RememberMeChanged -> {
                _uiState.value = _uiState.value.copy(rememberMe = event.value)
            }
            LoginEvent.Submit -> loginUser()
        }
    }

    private fun loginUser() {
        if (!validateInputs()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository
                .login(
                    UserCredentials(
                        username = _uiState.value.username,
                        password = _uiState.value.password,
                    ),
                ).collect { result ->
                    when (result) {
                        is Result.Error<DataError.Network> -> {
                        }
                        is Result.Success<LoginResponse> -> {
                            _uiState.value =
                                _uiState.value.copy(
                                    isLoading = false,
                                    error = null,
                                )
                            _navigationEvent.emit("home")
                        }
                    }
                }

//            catch (e: Exception) {
//                val errorMessage =
//                    when (e) {
//                        is IOException -> "Network error. Check your connection."
//                        is HttpException -> "Invalid credentials"
//                        else -> e.localizedMessage ?: "Unknown error occurred"
//                    }
//                _uiState.value = _uiState.value.copy(error = errorMessage)
//            } finally {
//                _uiState.value = _uiState.value.copy(isLoading = false)
//            }
        }
    }

    private fun validateInputs(): Boolean {
        val usernameError = validateUsername(_uiState.value.username)
        val passwordError = validatePassword(_uiState.value.password)

        _uiState.value =
            _uiState.value.copy(
                usernameError = usernameError,
                passwordError = passwordError,
            )

        return usernameError == null && passwordError == null
    }

    private fun validateUsername(username: String): String? =
        when {
            username.isBlank() -> "Username required"
            username.length < 3 -> "Username too short"
            else -> null
        }

    private fun validatePassword(password: String): String? =
        when {
            password.isBlank() -> "Password required"
            password.length < 6 -> "Password too short"
            else -> null
        }
}

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val usernameError: String? = null,
    val passwordError: String? = null,
    val rememberMe: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)

sealed class LoginEvent {
    data class UsernameChanged(
        val value: String,
    ) : LoginEvent()

    data class PasswordChanged(
        val value: String,
    ) : LoginEvent()

    data class RememberMeChanged(
        val value: Boolean,
    ) : LoginEvent()

    data object Submit : LoginEvent()
}
