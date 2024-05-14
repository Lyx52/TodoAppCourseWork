package com.example.ikme_app.data.todo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoTaskDao {
    @Query("SELECT * from tasks ORDER BY id ASC")
    fun getAllTasks(): Flow<List<TodoTask>>

    @Query("SELECT * from tasks WHERE userId = :userId")
    fun getTasksByUserId(userId: Int): Flow<List<TodoTask>>

    @Query("SELECT * from tasks WHERE id = :id")
    fun getTaskById(id: Int): Flow<TodoTask>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: TodoTask)

    @Update
    suspend fun update(item: TodoTask)

    @Delete
    suspend fun delete(item: TodoTask)
}