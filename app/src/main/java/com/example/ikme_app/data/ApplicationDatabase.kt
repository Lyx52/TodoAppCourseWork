package com.example.ikme_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ikme_app.data.todo.TodoTask
import com.example.ikme_app.data.todo.TodoTaskDao
import com.example.ikme_app.data.user.User
import com.example.ikme_app.data.user.UserDao

@Database(
    entities = [
        User::class,
        TodoTask::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun todoTaskDao(): TodoTaskDao
    companion object {
        @Volatile
        private var Instance: ApplicationDatabase? = null

        fun getDatabase(context: Context): ApplicationDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ApplicationDatabase::class.java, "appdb")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}