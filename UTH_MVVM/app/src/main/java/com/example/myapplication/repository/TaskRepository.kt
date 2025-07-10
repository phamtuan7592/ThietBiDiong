package com.example.myapplication.repository

import com.example.myapplication.model.Task
import com.example.myapplication.model.TaskPriority
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    suspend fun addTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(taskId: String)
}

class TaskRepositoryImpl : TaskRepository {
    private val _tasks = MutableStateFlow<List<Task>>(
        listOf(
            Task(
                title = "Complete Android Project",
                description = "Finish the UI, integrate API, and write documentation",
                priority = TaskPriority.MEDIUM
            ),
            Task(
                title = "Complete Android Project",
                description = "Finish the UI, integrate API, and write documentation",
                priority = TaskPriority.URGENT
            ),
            Task(
                title = "Complete Android Project",
                description = "Finish the UI, integrate API, and write documentation",
                priority = TaskPriority.HIGH
            ),
            Task(
                title = "Complete Android Project",
                description = "Finish the UI, integrate API, and write documentation",
                priority = TaskPriority.URGENT
            )
        )
    )

    override fun getAllTasks(): Flow<List<Task>> = _tasks.asStateFlow()

    override suspend fun addTask(task: Task) {
        val currentTasks = _tasks.value.toMutableList()
        currentTasks.add(0, task) // Add to top
        _tasks.value = currentTasks
    }

    override suspend fun updateTask(task: Task) {
        val currentTasks = _tasks.value.toMutableList()
        val index = currentTasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            currentTasks[index] = task
            _tasks.value = currentTasks
        }
    }

    override suspend fun deleteTask(taskId: String) {
        val currentTasks = _tasks.value.toMutableList()
        currentTasks.removeAll { it.id == taskId }
        _tasks.value = currentTasks
    }
}
