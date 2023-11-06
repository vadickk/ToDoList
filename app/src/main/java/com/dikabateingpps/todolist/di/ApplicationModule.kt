package com.dikabateingpps.todolist.di

import androidx.room.Room
import com.dikabateingpps.todolist.database.TodoAppDatabase
import com.dikabateingpps.todolist.domain.TodoRepository
import com.dikabateingpps.todolist.screens.add.AddTodoViewModel
import com.dikabateingpps.todolist.screens.list.TodoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ApplicationModule = module {
    viewModel {
        TodoListViewModel(get())
    }

    viewModel {
        AddTodoViewModel(get())
    }

    single<TodoAppDatabase> {
        Room.databaseBuilder(
            get(),
            TodoAppDatabase::class.java,
            "todo_application_database"
        ).fallbackToDestructiveMigration().build()
    }

    single<TodoRepository> {
        TodoRepository.Base(get<TodoAppDatabase>().dao)
    }
}