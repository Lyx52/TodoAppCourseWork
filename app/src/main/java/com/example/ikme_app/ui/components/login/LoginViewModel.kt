package com.example.ikme_app.ui.components.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ikme_app.data.user.User
import com.example.ikme_app.data.user.UserRepository
import com.example.ikme_app.ui.Utility
import kotlinx.coroutines.flow.first

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    var loginState: LoginState by mutableStateOf(LoginState())
        private set

    suspend fun tryLogin(): User? {
        if (validateInput(loginState)) {
            val user = userRepository.getUserByUsername(loginState.username).first() ?: return null;
            val passwordHash = Utility.hashPassword(loginState.password)
            if (passwordHash != user.passwordHash) {
                updateState(loginState.copy(errorMessage = "Username or Password is invalid"))
                return null
            }
            reset()
            return user
        }
        return null;
    }
    fun updateState(state: LoginState) {
        loginState = LoginState(state.username, state.password, state.errorMessage)
    }

    private fun validateInput(state: LoginState): Boolean {
        if (state.username.isEmpty()) {
            updateState(state.copy(errorMessage = "Username required"))
            return false;
        }
        if (state.password.isEmpty()) {
            updateState(state.copy(errorMessage = "Password required"))
            return false;
        }
        return true;
    }
    fun reset() {
        loginState = LoginState()
    }
}

data class LoginState(
    val username: String = "",
    val password: String = "",
    val errorMessage: String = ""
)