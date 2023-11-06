package com.dikabateingpps.todolist.screens.core

sealed interface TodoUiEvent {
    data class Navigate(val route: String): TodoUiEvent
}