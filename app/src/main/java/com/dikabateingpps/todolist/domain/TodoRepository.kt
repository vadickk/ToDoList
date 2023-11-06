package com.dikabateingpps.todolist.domain

import com.dikabateingpps.todolist.database.TodoDao
import com.dikabateingpps.todolist.database.TodoDataItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun addToDo(todoDataItem: TodoDataItem)
    fun getAllTodo(): Flow<List<TodoDataItem>>
    suspend fun updateTodo(todoDataItem: TodoDataItem)
    suspend fun deleteTodo(todoDataItem: TodoDataItem)
    suspend fun deleteAllTodos()

    class Base(
        private val todoDao: TodoDao
    ) : TodoRepository {
        override suspend fun addToDo(todoDataItem: TodoDataItem) {
            todoDao.insertTodo(todoDataItem)
        }

        override fun getAllTodo(): Flow<List<TodoDataItem>> {
            return todoDao.getTodos()
        }

        override suspend fun updateTodo(todoDataItem: TodoDataItem) {
            todoDao.updateTodo(todoDataItem)
        }

        override suspend fun deleteTodo(todoDataItem: TodoDataItem) {
            todoDao.deleteTodo(todoDataItem)
        }

        override suspend fun deleteAllTodos() {
            todoDao.deleteAllTodos()
        }
    }
}