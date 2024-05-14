package com.example.ikme_app.ui.components.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ikme_app.AppViewModelProvider
import com.example.ikme_app.R
import com.example.ikme_app.data.Routes
import com.example.ikme_app.ui.components.TodoItemList
import com.example.ikme_app.data.todo.TodoTask
import com.example.ikme_app.ui.components.AuthViewModel
import kotlinx.coroutines.launch

@Composable
fun TodoListScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier,
    todoListViewModel: TodoListViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var showAddItemDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val todoListState by todoListViewModel.todoListState.collectAsState()
    todoListViewModel.setUserId(authViewModel.authState.userId)
    Scaffold(
        topBar = {
            TodoListHeader(navHostController, authViewModel)
        },
        floatingActionButton = {
            Row {
                FloatingActionButton(
                    onClick = {
                      showAddItemDialog = true
                    },
                    modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Todo")
                }
            }

        }
    ) { innerPadding ->
        TodoItemList(todos = todoListState.taskList, onChanged = { updatedItem ->
            coroutineScope.launch {
                todoListViewModel.update(updatedItem)
            }
        }, onTaskDeleted = { deletedItem ->
            coroutineScope.launch {
                todoListViewModel.delete(deletedItem)
            }
        }, Modifier.padding(innerPadding))
    }
    if (showAddItemDialog) {
        TodoTaskModal(authViewModel.authState.userId, onDismiss = { showAddItemDialog = false }, onItemAdded = {
            coroutineScope.launch {
                todoListViewModel.create(it)
            }
        })
    }



}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListHeader(
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        navigationIcon = {
            IconButton(onClick = {
                authViewModel.reset()
                navHostController.navigate(Routes.LoginScreen.routeId)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Log out"
                )
            }
        },
        title = {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 8.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("IkMe Todo App")
                Text("User: ${authViewModel.authState.username}")
            }

        }
    )
}

@Composable
fun TodoTaskModal(
    userId: Int,
    onDismiss: () -> Unit,
    onItemAdded: (TodoTask) -> Unit) {
    var taskDescription by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.add_new_task)) },
        text = {
            OutlinedTextField(
                value = taskDescription,
                onValueChange = { taskDescription = it },
                label = { Text("Todo task description") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        },
        confirmButton = {
            Button(onClick = {
                if (taskDescription.isNotBlank()) {
                    onItemAdded(TodoTask(task = taskDescription, completed = false, userId = userId))
                    taskDescription = ""
                    onDismiss()
                }
            }) {
                Text(stringResource(R.string.add_button))
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text(stringResource(R.string.cancel_button))
            }
        }
    )
}