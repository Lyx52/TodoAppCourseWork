package com.example.ikme_app

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ikme_app.ui.components.AuthViewModel
import com.example.ikme_app.ui.components.login.LoginViewModel
import com.example.ikme_app.ui.components.register.RegisterViewModel
import com.example.ikme_app.ui.components.todo.TodoListViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            LoginViewModel(
                todoApplication().container.userRepository
            )
        }
        initializer {
            RegisterViewModel(
                todoApplication().container.userRepository
            )
        }
        initializer {
            AuthViewModel()
        }
        initializer {
            TodoListViewModel(
                todoApplication().container.taskRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [TodoApplication].
 */
fun CreationExtras.todoApplication(): TodoApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TodoApplication)