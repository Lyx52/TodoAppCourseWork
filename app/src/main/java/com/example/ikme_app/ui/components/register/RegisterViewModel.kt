package com.example.ikme_app.ui.components.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ikme_app.data.user.User
import com.example.ikme_app.data.user.UserRepository
import com.example.ikme_app.ui.Utility

class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    var registerState: RegisterState by mutableStateOf(RegisterState())
        private set

    suspend fun createUser(): Boolean {
        if (this.validateInput(registerState)) {
            userRepository.insertUser(User(
                username = registerState.username,
                passwordHash = Utility.hashPassword(registerState.password)
            ))
            reset()
            return true;
        }
        return false;
    }
    fun updateState(state: RegisterState) {
        registerState = RegisterState(state.username, state.password, state.confirmPassword, state.errorMessage)
    }
    fun reset() {
        registerState = RegisterState()
    }
    private fun validateInput(state: RegisterState): Boolean {
        if (state.username.isEmpty()) {
            updateState(state.copy(errorMessage = "Username required"))
            return false;
        }
        if (state.password.isEmpty()) {
            updateState(state.copy(errorMessage = "Password required"))
            return false;
        }
        if (state.password != state.confirmPassword) {
            updateState(state.copy(errorMessage = "Password's not equal"))
            return false;
        }
        if (state.password.length < 6) {
            updateState(state.copy(errorMessage = "Password must be at least 6 characters long"))
            return false;
        }
        updateState(state.copy(errorMessage = ""))
        return true;
    }

}

data class RegisterState(
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String = ""
)