package com.example.ikme_app.data.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.ikme_app.data.user.User

@Entity(
    tableName = "tasks",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class TodoTask(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var task: String = "",
    var completed: Boolean = false,
    @ColumnInfo(index = true)
    val userId: Int = 0
)