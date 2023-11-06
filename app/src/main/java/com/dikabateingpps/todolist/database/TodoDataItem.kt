package com.dikabateingpps.todolist.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoDataItem(
    val title: String,
    val isDone: Boolean = false,
    val description: String,
    @PrimaryKey val id: Int? = null
)
