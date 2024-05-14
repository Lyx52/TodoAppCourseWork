package com.example.ikme_app.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel() : ViewModel() {
    var authState: AuthState by mutableStateOf(AuthState())
        private set

    fun updateState(state: AuthState) {
        authState = AuthState(state.username, state.userId, state.loggedIn)
    }
    fun reset() {
        authState = AuthState()
    }
}

data class AuthState(
    val username: String = "",
    val userId: Int = 0,
    val loggedIn: Boolean = false
)