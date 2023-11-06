package com.dikabateingpps.todolist.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dikabateingpps.todolist.screens.add.AddTodoScreen
import com.dikabateingpps.todolist.utilites.Routes
import com.dikabateingpps.todolist.screens.list.TodoListScreen
import com.dikabateingpps.todolist.ui.theme.ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.TODO_LIST
                ) {
                    composable(Routes.TODO_LIST) {
                        TodoListScreen(
                            onNavigate = {
                                navController.navigate(it.route)
                            }
                        )
                    }

                    composable(
                        route = Routes.ADD_TODO
                    ) {
                        AddTodoScreen(
                            onPopBackStack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}