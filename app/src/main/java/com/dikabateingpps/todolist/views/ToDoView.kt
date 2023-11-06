package com.dikabateingpps.todolist.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dikabateingpps.todolist.database.TodoDataItem

@Composable
fun ToDoView(
    todoDataItem: TodoDataItem,
    updateDoneState: (Boolean, TodoDataItem) -> Unit,
    deleteItem: (TodoDataItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.White, shape = CircleShape)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = todoDataItem.title,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        textDecoration = if (todoDataItem.isDone) TextDecoration.LineThrough else TextDecoration.None,
                        color = if (todoDataItem.isDone) Color.Gray else Color.White
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = todoDataItem.description,
                    maxLines = 2,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        textDecoration = if (todoDataItem.isDone) TextDecoration.LineThrough else TextDecoration.None,
                        color = if (todoDataItem.isDone) Color.Gray else Color.White
                    ),
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row {
                Checkbox(
                    checked = todoDataItem.isDone,
                    onCheckedChange = { checked ->
                        updateDoneState.invoke(checked, todoDataItem)
                    },
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    modifier = Modifier.clickable {
                        deleteItem.invoke(todoDataItem)
                    }
                )
            }
        }
    }
}
