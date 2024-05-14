package com.example.ikme_app.data.todo

import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    /**
     *  Get all tasks
     */
    fun getAllTasks(): Flow<List<TodoTask>>

    /**
     * Get task by user id
     */
    fun getTasksByUserId(userId: Int): Flow<List<TodoTask>>

    /**
     * Get task by id
     */
    fun getTaskById(id: Int): Flow<TodoTask?>

    /**
     * Insert task
     */
    suspend fun insertTask(item: TodoTask)

    /**
     * Delete task
     */
    suspend fun deleteTask(item: TodoTask)

    /**
     * Update task
     */
    suspend fun updateTask(item: TodoTask)
}

class TaskRepositoryImpl(private val todoTaskDao: TodoTaskDao) : TaskRepository {
    override fun getAllTasks(): Flow<List<TodoTask>> = todoTaskDao.getAllTasks()

    override fun getTasksByUserId(userId: Int): Flow<List<TodoTask>> = todoTaskDao.getTasksByUserId(userId)

    override fun getTaskById(id: Int): Flow<TodoTask?> = todoTaskDao.getTaskById(id)

    override suspend fun insertTask(item: TodoTask) = todoTaskDao.insert(item)

    override suspend fun deleteTask(item: TodoTask) = todoTaskDao.delete(item)

    override suspend fun updateTask(item: TodoTask) = todoTaskDao.update(item)
}