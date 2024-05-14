package com.example.ikme_app.data.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    /**
     *  Get all users
     */
    fun getUsers(): Flow<List<User>>

    /**
     * Get user by id
     */
    fun getUserById(id: Int): Flow<User?>

    /**
     * Get user by username
     */
    fun getUserByUsername(username: String): Flow<User?>

    /**
     * Insert user
     */
    suspend fun insertUser(item: User)

    /**
     * Delete user
     */
    suspend fun deleteUser(item: User)

    /**
     * Update user
     */
    suspend fun updateUser(item: User)
}

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun getUsers(): Flow<List<User>> = userDao.getAllItems()

    override fun getUserById(id: Int): Flow<User?> = userDao.getUserById(id)

    override fun getUserByUsername(username: String): Flow<User?> = userDao.getUserByName(username)

    override suspend fun insertUser(item: User) = userDao.insert(item)

    override suspend fun deleteUser(item: User) = userDao.delete(item)

    override suspend fun updateUser(item: User) = userDao.update(item)
}