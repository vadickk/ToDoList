package com.dikabateingpps.todolist.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dikabateingpps.todolist.database.TodoDataItem
import com.dikabateingpps.todolist.domain.TodoRepository
import com.dikabateingpps.todolist.screens.core.TodoUiEvent
import com.dikabateingpps.todolist.utilites.Routes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class TodoListViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    val listOfTodo = todoRepository.getAllTodo()

    private val _uiEvent =  Channel<TodoUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(todoListEvent: TodoListEvent) {
        when(todoListEvent) {
            is TodoListEvent.OnAddToDoEvent -> {
                performEvent(TodoUiEvent.Navigate(Routes.ADD_TODO))
            }

            is TodoListEvent.OnToDoClick -> TODO()
        }
    }

    fun updateTodo(todoDataItem: TodoDataItem) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.updateTodo(todoDataItem)
        }
    }

    fun deleteTodo(todoDataItem: TodoDataItem) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteTodo(todoDataItem)
        }
    }

    fun deleteAllTodos() {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.deleteAllTodos()
        }
    }
    private fun performEvent(todoUiEvent: TodoUiEvent) {
        viewModelScope.launch { _uiEvent.send(todoUiEvent) }
    }
}