package com.dikabateingpps.todolist.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dikabateingpps.todolist.database.TodoDataItem
import com.dikabateingpps.todolist.domain.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTodoViewModel(
    private val todoRepository: TodoRepository
) : ViewModel() {

    fun addToDo(todoDataItem: TodoDataItem) {
        viewModelScope.launch(Dispatchers.IO) {
            todoRepository.addToDo(todoDataItem)
        }
    }
}