package com.example.ikme_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ikme_app.data.todo.TodoTask

@Composable
fun TodoItemList(
    todos: List<TodoTask>,
    onChanged: (TodoTask) -> Unit,
    onTaskDeleted: (TodoTask) -> Unit,
    modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(todos) { item ->
            TodoItemRow(item, onChanged, onTaskDeleted, modifier)
        }
    }
}