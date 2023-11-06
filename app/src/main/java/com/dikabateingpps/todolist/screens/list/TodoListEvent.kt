package com.dikabateingpps.todolist.screens.list

import com.dikabateingpps.todolist.database.TodoDataItem

sealed interface TodoListEvent {
    data object OnAddToDoEvent : TodoListEvent
    data class OnToDoClick(val todoDataItem: TodoDataItem) : TodoListEvent
}