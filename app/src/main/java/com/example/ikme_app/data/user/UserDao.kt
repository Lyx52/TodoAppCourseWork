package com.example.ikme_app.data.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * from users ORDER BY username ASC")
    fun getAllItems(): Flow<List<User>>

    @Query("SELECT * from users WHERE id = :id")
    fun getUserById(id: Int): Flow<User>

    @Query("SELECT * from users WHERE username = :username")
    fun getUserByName(username: String): Flow<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: User)

    @Update
    suspend fun update(item: User)

    @Delete
    suspend fun delete(item: User)
}