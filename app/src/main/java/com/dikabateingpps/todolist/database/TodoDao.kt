package com.dikabateingpps.todolist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todoDataItem: TodoDataItem)

    @Update
    suspend fun updateTodo(todoDataItem: TodoDataItem)

    @Delete
    suspend fun deleteTodo(todoDataItem: TodoDataItem)

    @Query("DELETE FROM todo_table")
    fun deleteAllTodos()

    @Query("SELECT * FROM todo_table")
    fun getTodos(): Flow<List<TodoDataItem>>
}