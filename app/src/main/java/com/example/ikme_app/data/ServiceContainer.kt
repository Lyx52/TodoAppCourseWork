package com.example.ikme_app.data

import android.content.Context
import com.example.ikme_app.data.todo.TaskRepository
import com.example.ikme_app.data.todo.TaskRepositoryImpl
import com.example.ikme_app.data.user.UserRepository
import com.example.ikme_app.data.user.UserRepositoryImpl

interface ServiceContainer {
    val userRepository: UserRepository
    val taskRepository: TaskRepository
}

class ServiceContainerImpl(private val context: Context) : ServiceContainer {
    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(ApplicationDatabase.getDatabase(context).userDao())
    }
    override val taskRepository: TaskRepository by lazy {
        TaskRepositoryImpl(ApplicationDatabase.getDatabase(context).todoTaskDao())
    }
}