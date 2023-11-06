package com.dikabateingpps.todolist.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.dikabateingpps.todolist.screens.core.TodoUiEvent
import com.dikabateingpps.todolist.ui.theme.BackgroundMainColor
import com.dikabateingpps.todolist.views.ToDoView
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TodoListScreen(
    onNavigate: (TodoUiEvent.Navigate) -> Unit,
    viewModel: TodoListViewModel = koinViewModel()
) {
    val todos = viewModel.listOfTodo.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is TodoUiEvent.Navigate -> onNavigate(event)
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Todo List") },
                actions = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete ALl",
                        modifier = Modifier.clickable {
                            viewModel.deleteAllTodos()
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(TodoListEvent.OnAddToDoEvent)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        content = {
            LazyColumn(
                modifier = Modifier.padding(top = it.calculateTopPadding())
            ) {
                items(todos.value) { todo ->
                    ToDoView(
                        todoDataItem = todo,
                        updateDoneState = { checked, todoItem ->
                            viewModel.updateTodo(todoItem.copy(isDone = checked))
                        },
                        deleteItem = { viewModel.deleteTodo(todo) }
                    )
                }
            }
        }
    )
}
