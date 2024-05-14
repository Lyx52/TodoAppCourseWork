package com.example.ikme_app.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ikme_app.data.todo.TodoTask

@Composable
fun TodoItemRow(
    item: TodoTask,
    onChanged: (TodoTask) -> Unit,
    onDelete: (TodoTask) -> Unit,
    modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.task)
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = item.completed,
            onCheckedChange = { isChecked ->
                val updatedItem = item.copy(completed = isChecked)
                onChanged(updatedItem)
            }
        )
        Button(
            onClick = {
                onDelete(item)
            },
            colors = ButtonColors(
                MaterialTheme.colorScheme.background,
                MaterialTheme.colorScheme.error,
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.colorScheme.primary
            ),
            shape = ShapeDefaults.Small
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove Todo")
        }
    }
}