package com.dikabateingpps.todolist.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dikabateingpps.todolist.database.TodoDataItem
import com.dikabateingpps.todolist.screens.list.TodoListViewModel
import com.dikabateingpps.todolist.ui.theme.ToDoListTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddTodoViewModel = koinViewModel()
) {
    var titleText by remember { mutableStateOf(TextFieldValue()) }
    var descriptionText by remember { mutableStateOf(TextFieldValue()) }
    var titleError by remember { mutableStateOf<String?>(null) }
    var descriptionError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        TopAppBar(
            title = {
                Text(text = "Add To-Do")
            },
            navigationIcon = {
                IconButton(onClick = { onPopBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = titleText,
            onValueChange = {
                titleText = it
                titleError = null
            },
            label = { Text("Title") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            isError = titleError != null,
            trailingIcon = {
                if (titleError != null) {
                    Icon(Icons.Default.Warning, contentDescription = "Error")
                }
            },
        )

        if (titleError != null) {
            Text(
                text = titleError!!,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = descriptionText,
            onValueChange = {
                descriptionText = it
                descriptionError = null
            },
            label = { Text("Description") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.White),
            isError = descriptionError != null,
            trailingIcon = {
                if (descriptionError != null) {
                    Icon(Icons.Default.Warning, contentDescription = "Error")
                }
            },
        )

        if (descriptionError != null) {
            Text(
                text = descriptionError!!,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (titleText.text.isNotEmpty() && descriptionText.text.isNotEmpty()) {
                    viewModel.addToDo(
                        TodoDataItem(
                            title = titleText.text,
                            description = descriptionText.text
                        )
                    )
                    onPopBackStack.invoke()
                } else {
                    if (titleText.text.isEmpty()) {
                        titleError = "Title cannot be empty"
                    }
                    if (descriptionText.text.isEmpty()) {
                        descriptionError = "Description cannot be empty"
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(60.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Save ToDo")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddToDoScreenPreview() {
    ToDoListTheme {
        AddTodoScreen(
            onPopBackStack = { /*TODO*/ }
        )
    }
}
