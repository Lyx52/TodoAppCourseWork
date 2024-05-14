package com.example.ikme_app.ui.components.todo

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ikme_app.data.todo.TaskRepository
import com.example.ikme_app.data.todo.TodoTask
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TodoListViewModel(
    private val todoTaskRepository: TaskRepository
) : ViewModel() {
    var userId: MutableState<Int> = mutableIntStateOf(0)
    var todoListState: StateFlow<TodoListState> = MutableStateFlow(TodoListState())
    fun setUserId(userId: Int) {
        todoListState = todoTaskRepository.getTasksByUserId(userId).map { TodoListState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = TodoListState()
            )
    }
    suspend fun create(task: TodoTask) {
        todoTaskRepository.insertTask(task)
    }
    suspend fun delete(task: TodoTask) {
        todoTaskRepository.deleteTask(task)
    }
    suspend fun update(task: TodoTask) {
        todoTaskRepository.updateTask(task)
    }
}

data class TodoListState(
    var taskList: List<TodoTask> = listOf()
)